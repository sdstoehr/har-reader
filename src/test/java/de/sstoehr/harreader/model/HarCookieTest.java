package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

class HarCookieTest extends AbstractMapperTest<HarCookie> {

    private final static Date EXPECTED_EXPIRES = new Date() {{
        setTime(1388577600000L);
    }};

    @Override
    public void testMapping() {
        HarCookie cookie = map("{\"name\":\"aName\",\"value\":\"aValue\",\"path\":\"/\",\"domain\":\"sstoehr.de\"," +
    "\"expires\":\"2014-01-01T12:00:00\",\"httpOnly\":\"true\",\"secure\":\"false\",\"comment\":\"my comment\",\"_unknown\":\"unknown\"}", HarCookie.class);

        assertNotNull(cookie);
        assertEquals("aName", cookie.getName());
        assertEquals("aValue", cookie.getValue());
        assertEquals("/", cookie.getPath());
        assertEquals("sstoehr.de", cookie.getDomain());
        assertEquals(EXPECTED_EXPIRES, cookie.getExpires());
        assertEquals(true, cookie.getHttpOnly());
        assertEquals(false, cookie.getSecure());
        assertEquals("my comment", cookie.getComment());

        assertNotNull(cookie.getAdditional());
        assertEquals("unknown", cookie.getAdditional().get("_unknown"));

        cookie = map(UNKNOWN_PROPERTY, HarCookie.class);
        assertNotNull(cookie);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarCookie.class).verify();
    }
}
