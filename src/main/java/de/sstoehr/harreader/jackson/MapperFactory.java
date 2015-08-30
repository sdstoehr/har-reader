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
        module.addDeserializer(Date.class, new HttpDateDeserializer(mode));
        mapper.registerModule(module);
        return mapper;
    }

}
