package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class HarPageTimingTest extends AbstractMapperTest<HarPageTiming> {

    private static final Integer EXPECTED_DEFAULT_DURATION = -1;

    @Test
    void testOnContentLoad() {
        HarPageTiming pageTiming = new HarPageTiming();
        assertEquals(EXPECTED_DEFAULT_DURATION, pageTiming.onContentLoad());

        pageTiming = new HarPageTiming(1234, null, null, null);
        assertEquals(1234, (int) pageTiming.onContentLoad());
    }

    @Test
    void testOnLoad() {
        HarPageTiming pageTiming = new HarPageTiming();
        assertEquals(EXPECTED_DEFAULT_DURATION, pageTiming.onLoad());

        pageTiming = new HarPageTiming(null, 1234, null, null);
        assertEquals(1234, (int) pageTiming.onLoad());
    }

    @Override
    @Test
    void testMapping() {
        HarPageTiming pageTiming = map("{\"onContentLoad\": 1234, \"onLoad\": 5678, \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarPageTiming.class);

        assertEquals(1234, (int) pageTiming.onContentLoad());
        assertEquals(5678, (int) pageTiming.onLoad());
        assertEquals("My comment", pageTiming.comment());

        assertNotNull(pageTiming.additional());
        assertEquals("unknown", pageTiming.additional().get("_unknown"));

        pageTiming = map(UNKNOWN_PROPERTY, HarPageTiming.class);
        assertNotNull(pageTiming);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarPageTiming.class).verify();
    }
}
