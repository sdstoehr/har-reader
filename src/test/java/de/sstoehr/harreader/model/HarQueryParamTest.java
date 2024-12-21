package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarQueryParamTest extends AbstractMapperTest<HarQueryParam> {

    @Override
    @Test
    void testMapping() {
        HarQueryParam queryParam = map("{\"name\": \"aName\", \"value\":\"aValue\", \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarQueryParam.class);

        assertEquals("aName", queryParam.name());
        assertEquals("aValue", queryParam.value());
        assertEquals("My comment", queryParam.comment());

        assertNotNull(queryParam.additional());
        assertEquals("unknown", queryParam.additional().get("_unknown"));
    }

    @Test
    void testNullability() {
        testNullability(new HarQueryParam());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarQueryParam.class).verify();
    }
}