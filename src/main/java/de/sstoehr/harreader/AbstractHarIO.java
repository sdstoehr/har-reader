package de.sstoehr.harreader;

import de.sstoehr.harreader.jackson.DefaultMapperFactory;
import de.sstoehr.harreader.jackson.MapperFactory;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.util.function.Function;

public abstract class AbstractHarIO {
    private final MapperFactory mapperFactory;

    public AbstractHarIO(MapperFactory mapperFactory) {
        if (mapperFactory == null) {
            throw new IllegalArgumentException("mapperFactory must not be null!");
        }
        this.mapperFactory = mapperFactory;
    }

    public AbstractHarIO() {
        this(new DefaultMapperFactory());
    }

    protected MapperFactory getMapperFactory() {
        return mapperFactory;
    }

    protected static <T, E extends Exception> T wrap(ObjectMapper mapper, IOFunction<ObjectMapper, T> consumer,
            Function<JacksonException, E> exceptionFactory) throws E {
        try {
            return consumer.apply(mapper);
        } catch(JacksonException thrown) {
            throw exceptionFactory.apply(thrown);
        }
    }

    @FunctionalInterface
    protected interface IOFunction<T, R> {

        R apply(T object) throws JacksonException;

    }

}
