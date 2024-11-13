package de.sstoehr.harreader;

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

}
