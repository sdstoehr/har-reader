package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

class HarCacheTest extends AbstractMapperTest<HarCache> {

    private final static Date EXPECTED_EXPIRES = new Date() {{
        setTime(1388577600000L);
    }};
    private final static Date EXPECTED_LAST_ACCESS = new Date() {{
        setTime(1370088000000L);
    }};

    @Override
    public void testMapping() {
        HarCache cache = map("{\"beforeRequest\":{\"expires\":\"2014-01-01T12:00:00\",\"lastAccess\":\"2013-06-01T12:00:00\",\"eTag\":\"abc123\"," +
        "\"hitCount\":3,\"comment\":\"my comment\"},\"afterRequest\":{},\"comment\":\"my comment 2\",\"_unknown\":\"unknown\"}", HarCache.class);

        assertNotNull(cache.getBeforeRequest());
        assertEquals(EXPECTED_EXPIRES, cache.getBeforeRequest().getExpires());
        assertEquals(EXPECTED_LAST_ACCESS, cache.getBeforeRequest().getLastAccess());
        assertEquals("abc123", cache.getBeforeRequest().geteTag());
        assertEquals(3, (long) cache.getBeforeRequest().getHitCount());
        assertEquals("my comment", cache.getBeforeRequest().getComment());

        assertNotNull(cache.getAfterRequest());

        assertEquals("my comment 2", cache.getComment());

        assertNotNull(cache.getAdditional());
        assertEquals("unknown", cache.getAdditional().get("_unknown"));

        cache = map(UNKNOWN_PROPERTY, HarCache.class);
        assertNotNull(cache);

    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarCache.class).verify();
        EqualsVerifier.simple().forClass(HarCache.HarCacheInfo.class).verify();
    }
}
