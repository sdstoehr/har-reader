package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HarCacheInfoTest extends AbstractMapperTest<HarCache.HarCacheInfo> {

    private final static ZonedDateTime EXPECTED_EXPIRES = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1388577600000L), ZoneId.of("Z"));
    private final static ZonedDateTime EXPECTED_LAST_ACCESS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1370088000000L), ZoneId.of("Z"));

    @Override
    @Test
    void testMapping() {
        HarCache.HarCacheInfo cacheInfo = map("{\"expires\":\"2014-01-01T12:00:00Z\",\"lastAccess\":\"2013-06-01T12:00:00Z\",\"eTag\":\"abc123\"," +
                "\"hitCount\":3,\"comment\":\"my comment\", \"_unknown\":\"unknown\"}", HarCache.HarCacheInfo.class);

        assertEquals(EXPECTED_EXPIRES, cacheInfo.expires());
        assertEquals(EXPECTED_LAST_ACCESS, cacheInfo.lastAccess());
        assertEquals("abc123", cacheInfo.eTag());
        assertEquals(3, (long) cacheInfo.hitCount());
        assertEquals("my comment", cacheInfo.comment());

        assertNotNull(cacheInfo.additional());
        assertEquals("unknown", cacheInfo.additional().get("_unknown"));
    }

    @Test
    void testNullability() {
        testNullability(new HarCache.HarCacheInfo());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarCache.HarCacheInfo.class).verify();
    }
}
