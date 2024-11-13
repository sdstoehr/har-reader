package de.sstoehr.harreader;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.sstoehr.harreader.jackson.MapperFactory;
import de.sstoehr.harreader.model.Har;

public class HarReader extends AbstractHarIO {

    public HarReader() {
        super();
    }
    
    public HarReader(MapperFactory mapperFactory) {
        super(mapperFactory);
    }

    public Har readFromFile(File har) throws HarReaderException {
        return this.readFromFile(har, HarReaderMode.STRICT);
    }

    public Har readFromFile(File har, HarReaderMode mode) throws HarReaderException {
        ObjectMapper mapper = getMapperFactory().instance(mode);
        try {
            return mapper.readValue(har, Har.class);
        } catch (IOException e) {
            throw new HarReaderException(e);
        }
    }

    public Har readFromString(String har) throws HarReaderException {
        return this.readFromString(har, HarReaderMode.STRICT);
    }

    public Har readFromString(String har, HarReaderMode mode) throws HarReaderException {
        ObjectMapper mapper = getMapperFactory().instance(mode);
        try {
            return mapper.readValue(har, Har.class);
        } catch (IOException e) {
            throw new HarReaderException(e);
        }
    }

}
