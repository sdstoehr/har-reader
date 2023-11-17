package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarQueryParamTest extends AbstractMapperTest<HarQueryParam> {

    @Override
    void testMapping() {
        HarQueryParam queryParam = map("{\"name\": \"aName\", \"value\":\"aValue\", \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarQueryParam.class);

        assertEquals("aName", queryParam.getName());
        assertEquals("aValue", queryParam.getValue());
        assertEquals("My comment", queryParam.getComment());

        assertNotNull(queryParam.getAdditional());
        assertEquals("unknown", queryParam.getAdditional().get("_unknown"));
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarQueryParam.class).verify();
    }
}