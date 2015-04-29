package de.sstoehr.harreader.jackson;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.sstoehr.harreader.HarReaderMode;

public class MapperFactory {

    private MapperFactory() {
    }

    public static ObjectMapper instance(HarReaderMode mode) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        if (mode == HarReaderMode.LAX) {
            module.addDeserializer(Date.class, new ExceptionIgnoringDateDeserializer());
        }
        mapper.registerModule(module);
        return mapper;
    }

}
