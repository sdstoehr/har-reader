package de.sstoehr.harreader.jackson;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
public class MapperFactory {
    public static ObjectMapper instance() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new ExceptionIgnoringDateDeserializer());
        mapper.registerModule(module);
        return mapper;
    }
}
