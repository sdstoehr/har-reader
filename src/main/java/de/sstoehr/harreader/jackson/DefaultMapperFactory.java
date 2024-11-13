package de.sstoehr.harreader.jackson;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import de.sstoehr.harreader.HarReaderMode;

public class DefaultMapperFactory implements MapperFactory {

    public ObjectMapper instance(HarReaderMode mode) {
        SimpleModule module = new SimpleModule();
        if (mode == HarReaderMode.LAX) {
            module.addDeserializer(Date.class, new ExceptionIgnoringDateDeserializer());
            module.addDeserializer(Integer.class, new ExceptionIgnoringIntegerDeserializer());
        }
        return instance().registerModule(module);
    }

    public ObjectMapper instance() {
        return new ObjectMapper();
    }

}
