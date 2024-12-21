package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.sstoehr.harreader.jackson.DefaultMapperFactory;
import org.junit.jupiter.api.Assertions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractMapperTest<T> {

    protected final static String UNKNOWN_PROPERTY = "{\"unknownProperty\":\"value\"}";

    abstract void testMapping();

    public T map(String input, Class<T> tClass) {
        ObjectMapper mapper = (new DefaultMapperFactory()).instance();
        try {
            return mapper.readValue(input, tClass);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return null;
    }

    public void testNullability(T object) {
        Arrays.stream(object.getClass().getDeclaredFields())
                .forEach(f -> {
                    f.setAccessible(true);
                    try {
                        if (f.isAnnotationPresent(Nonnull.class)) {
                            Assertions.assertNotNull(f.get(object), "Field " + f.getName() + " should not be null");
                        } else if (f.isAnnotationPresent(Nullable.class)) {
                            Assertions.assertNull(f.get(object), "Field " + f.getName() + " should be null");
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
