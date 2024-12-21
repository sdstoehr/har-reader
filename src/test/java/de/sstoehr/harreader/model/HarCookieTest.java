package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

class HarCookieTest extends AbstractMapperTest<HarCookie> {

    private final static ZonedDateTime EXPECTED_EXPIRES = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1388577600000L), ZoneId.of("Z"));

    @Override
    @Test
    public void testMapping() {
        HarCookie cookie = map("{\"name\":\"aName\",\"value\":\"aValue\",\"path\":\"/\",\"domain\":\"sstoehr.de\"," +
    "\"expires\":\"2014-01-01T12:00:00Z\",\"httpOnly\":\"true\",\"secure\":\"false\",\"comment\":\"my comment\",\"_unknown\":\"unknown\"}", HarCookie.class);

        assertNotNull(cookie);
        assertEquals("aName", cookie.name());
        assertEquals("aValue", cookie.value());
        assertEquals("/", cookie.path());
        assertEquals("sstoehr.de", cookie.domain());
        assertEquals(EXPECTED_EXPIRES, cookie.expires());
        assertEquals(true, cookie.httpOnly());
        assertEquals(false, cookie.secure());
        assertEquals("my comment", cookie.comment());

        assertNotNull(cookie.additional());
        assertEquals("unknown", cookie.additional().get("_unknown"));

        cookie = map(UNKNOWN_PROPERTY, HarCookie.class);
        assertNotNull(cookie);
    }

    @Test
    void testNullability() {
        testNullability(new HarCookie());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarCookie.class).verify();
    }
}
