package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractMapperTest<T> {

    protected final static String UNKNOWN_PROPERTY = "{\"unknownProperty\":\"value\"}";

    @Test
    abstract void testMapping();

    public T map(String input, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(input, tClass);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return null;
    }
}
