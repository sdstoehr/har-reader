package de.sstoehr.harreader;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.sstoehr.harreader.jackson.MapperFactory;
import de.sstoehr.harreader.model.Har;

public class HarReader {

    public static Har fromFile(File har) throws HarReaderException {
        ObjectMapper mapper = MapperFactory.instance();
        try {
            return mapper.readValue(har, Har.class);
        } catch (IOException e) {
            throw new HarReaderException(e);
        }
    }

    public static Har fromString(String har) throws HarReaderException {
        ObjectMapper mapper = MapperFactory.instance();
        try {
            return mapper.readValue(har, Har.class);
        } catch (IOException e) {
            throw new HarReaderException(e);
        }
    }

}
