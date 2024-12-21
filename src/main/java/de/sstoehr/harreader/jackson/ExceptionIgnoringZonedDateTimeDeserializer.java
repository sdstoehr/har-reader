package de.sstoehr.harreader.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ExceptionIgnoringZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        try {
            return InstantDeserializer.ZONED_DATE_TIME.deserialize(jp, ctxt);
        } catch (IOException e) {
            //ignore
        }
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(0L), ZoneId.of("UTC"));
    }

}
