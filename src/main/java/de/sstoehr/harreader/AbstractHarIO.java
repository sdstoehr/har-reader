package de.sstoehr.harreader;

import java.io.IOException;
import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.sstoehr.harreader.jackson.DefaultMapperFactory;
import de.sstoehr.harreader.jackson.MapperFactory;

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
            Function<IOException, E> exceptionFactory) throws E {
        try {
            return consumer.apply(mapper);
        } catch(IOException thrown) {
            throw exceptionFactory.apply(thrown);
        }
    }

    @FunctionalInterface
    protected interface IOFunction<T, R> {

        R apply(T object) throws IOException;

    }

}
