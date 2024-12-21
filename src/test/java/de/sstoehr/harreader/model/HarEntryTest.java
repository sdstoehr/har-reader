package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

public class HarEntryTest extends AbstractMapperTest<HarEntry> {

    private final static ZonedDateTime EXPECTED_STARTED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1388577600000L), ZoneId.of("Z"));

    @Override
    @Test
    public void testMapping() {
        HarEntry entry = map("{\"pageref\":\"aPageref\",\"startedDateTime\":\"2014-01-01T12:00:00Z\",\"time\":12345,"
        + "\"request\":{},\"response\":{},\"cache\":{},\"timings\":{},\"serverIPAddress\":\"1.2.3.4\",\"connection\":\"aConnection\","
        + "\"comment\":\"my comment\", \"_add\": \"additional info\"}", HarEntry.class);

        assertNotNull(entry);
        assertEquals("aPageref", entry.pageref());
        assertEquals(EXPECTED_STARTED, entry.startedDateTime());
        assertEquals(12345, (int) entry.time());
        assertNotNull(entry.request());
        assertNotNull(entry.response());
        assertNotNull(entry.cache());
        assertNotNull(entry.timings());
        assertEquals("1.2.3.4", entry.serverIPAddress());
        assertEquals("aConnection", entry.connection());
        assertEquals("my comment", entry.comment());
        assertEquals("additional info", entry.additional().get("_add"));

        entry = map(UNKNOWN_PROPERTY, HarEntry.class);
        assertNotNull(entry);
    }

    @Test
    public void testRequestNull() {
        HarEntry entry = new HarEntry();
        assertNotNull(entry.request());
    }

    @Test
    public void testResponseNull() {
        HarEntry entry = new HarEntry();
        assertNotNull(entry.response());
    }

    @Test
    public void testCacheNull() {
        HarEntry entry = new HarEntry();
        assertNotNull(entry.cache());
    }

    @Test
    public void testTimingsNull() {
        HarEntry entry = new HarEntry();
        assertNotNull(entry.timings());
    }

    @Test
    void testNullability() {
        testNullability(new HarEntry());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.simple().forClass(HarEntry.class).verify();
    }
}
