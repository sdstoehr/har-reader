package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarTest extends AbstractMapperTest<Har> {

    @Test
    void testLogNull() {
        Har har = new Har(null);
        assertNotNull(har.log());
    }

    @Override
    @Test
    void testMapping() {
        Har har = map("{\"log\": {}}", Har.class);
        assertNotNull(har.log());

        har = map(UNKNOWN_PROPERTY, Har.class);
        assertNotNull(har);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(Har.class).verify();
    }

}
