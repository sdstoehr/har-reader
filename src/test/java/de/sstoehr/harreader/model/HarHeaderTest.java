package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarHeaderTest extends AbstractMapperTest<HarHeader> {

    @Override
    public void testMapping() {
        HarHeader header = map("{\"name\":\"aName\",\"value\":\"aValue\",\"comment\":\"my comment\",\"_unknown\":\"unknown\"}", HarHeader.class);

        assertNotNull(header);
        assertEquals("aName", header.getName());
        assertEquals("aValue", header.getValue());
        assertEquals("my comment", header.getComment());

        assertNotNull(header.getAdditional());
        assertEquals("unknown", header.getAdditional().get("_unknown"));

        header = map(UNKNOWN_PROPERTY, HarHeader.class);
        assertNotNull(header);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarHeader.class).verify();
    }
}
