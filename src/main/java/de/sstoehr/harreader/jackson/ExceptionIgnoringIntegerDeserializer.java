package de.sstoehr.harreader.jackson;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.jdk.NumberDeserializers;

public class ExceptionIgnoringIntegerDeserializer extends ValueDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser jp, DeserializationContext ctxt) {
        try {
            NumberDeserializers.IntegerDeserializer integerDeserializer = new NumberDeserializers.IntegerDeserializer(Integer.class, null);
            return integerDeserializer.deserialize(jp, ctxt);
        } catch (JacksonException ignore) {
            return null;
        }
    }
}
