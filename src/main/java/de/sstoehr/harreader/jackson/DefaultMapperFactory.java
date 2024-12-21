package de.sstoehr.harreader.jackson;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.sstoehr.harreader.HarReaderMode;

public class DefaultMapperFactory implements MapperFactory {

    public ObjectMapper instance(HarReaderMode mode) {
        SimpleModule module = new SimpleModule();
        if (mode == HarReaderMode.LAX) {
            module.addDeserializer(ZonedDateTime.class, new ExceptionIgnoringZonedDateTimeDeserializer());
            module.addDeserializer(Integer.class, new ExceptionIgnoringIntegerDeserializer());
        }
        return instance().registerModule(module);
    }

    public ObjectMapper instance() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);

        return mapper;
    }

}
