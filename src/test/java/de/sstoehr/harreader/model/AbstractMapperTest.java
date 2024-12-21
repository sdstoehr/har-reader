package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.sstoehr.harreader.jackson.DefaultMapperFactory;

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
}
