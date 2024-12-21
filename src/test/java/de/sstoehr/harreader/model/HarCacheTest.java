package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

class HarCacheTest extends AbstractMapperTest<HarCache> {

    private final static ZonedDateTime EXPECTED_EXPIRES = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1388577600000L), ZoneId.of("Z"));
    private final static ZonedDateTime EXPECTED_LAST_ACCESS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1370088000000L), ZoneId.of("Z"));

    @Override
    @Test
    public void testMapping() {
        HarCache cache = map("{\"beforeRequest\":{\"expires\":\"2014-01-01T12:00:00Z\",\"lastAccess\":\"2013-06-01T12:00:00Z\",\"eTag\":\"abc123\"," +
        "\"hitCount\":3,\"comment\":\"my comment\"},\"afterRequest\":{},\"comment\":\"my comment 2\",\"_unknown\":\"unknown\"}", HarCache.class);

        assertNotNull(cache.beforeRequest());
        assertEquals(EXPECTED_EXPIRES, cache.beforeRequest().expires());
        assertEquals(EXPECTED_LAST_ACCESS, cache.beforeRequest().lastAccess());
        assertEquals("abc123", cache.beforeRequest().eTag());
        assertEquals(3, (long) cache.beforeRequest().hitCount());
        assertEquals("my comment", cache.beforeRequest().comment());

        assertNotNull(cache.afterRequest());

        assertEquals("my comment 2", cache.comment());

        assertNotNull(cache.additional());
        assertEquals("unknown", cache.additional().get("_unknown"));

        cache = map(UNKNOWN_PROPERTY, HarCache.class);
        assertNotNull(cache);

    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarCache.class).verify();
        EqualsVerifier.simple().forClass(HarCache.HarCacheInfo.class).verify();
    }
}
