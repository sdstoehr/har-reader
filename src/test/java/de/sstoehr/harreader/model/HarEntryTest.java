package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class HarEntryTest extends AbstractMapperTest<HarEntry> {

    private final static Date EXPECTED_STARTED = new Date() {{
        setTime(1388577600000L);
    }};

    @Override
    public void testMapping() {
        HarEntry entry = map("{\"pageref\":\"aPageref\",\"startedDateTime\":\"2014-01-01T12:00:00\",\"time\":12345,"
        + "\"request\":{},\"response\":{},\"cache\":{},\"timings\":{},\"serverIPAddress\":\"1.2.3.4\",\"connection\":\"aConnection\","
        + "\"comment\":\"my comment\", \"_add\": \"additional info\"}", HarEntry.class);

        assertNotNull(entry);
        assertEquals("aPageref", entry.getPageref());
        assertEquals(EXPECTED_STARTED, entry.getStartedDateTime());
        assertEquals(12345, (int) entry.getTime());
        assertNotNull(entry.getRequest());
        assertNotNull(entry.getResponse());
        assertNotNull(entry.getCache());
        assertNotNull(entry.getTimings());
        assertEquals("1.2.3.4", entry.getServerIPAddress());
        assertEquals("aConnection", entry.getConnection());
        assertEquals("my comment", entry.getComment());
        assertEquals("additional info", entry.getAdditional().get("_add"));

        entry = map(UNKNOWN_PROPERTY, HarEntry.class);
        assertNotNull(entry);
    }

    @Test
    public void testRequestNull() {
        HarEntry entry = new HarEntry();
        entry.setRequest(null);
        assertNotNull(entry.getRequest());
    }

    @Test
    public void testResponseNull() {
        HarEntry entry = new HarEntry();
        entry.setResponse(null);
        assertNotNull(entry.getResponse());
    }

    @Test
    public void testCacheNull() {
        HarEntry entry = new HarEntry();
        entry.setCache(null);
        assertNotNull(entry.getCache());
    }

    @Test
    public void testTimingsNull() {
        HarEntry entry = new HarEntry();
        entry.setTimings(null);
        assertNotNull(entry.getTimings());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.simple().forClass(HarEntry.class).verify();
    }
}
