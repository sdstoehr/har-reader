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
        assertEquals(EXPECTED_DEFAULT_DURATION, pageTiming.getOnContentLoad());

        pageTiming.setOnContentLoad(1234);
        assertEquals(1234, (int) pageTiming.getOnContentLoad());

        pageTiming.setOnContentLoad(null);
        assertEquals(EXPECTED_DEFAULT_DURATION, pageTiming.getOnContentLoad());
    }

    @Test
    void testOnLoad() {
        HarPageTiming pageTiming = new HarPageTiming();
        assertEquals(EXPECTED_DEFAULT_DURATION, pageTiming.getOnLoad());

        pageTiming.setOnLoad(1234);
        assertEquals(1234, (int) pageTiming.getOnLoad());

        pageTiming.setOnLoad(null);
        assertEquals(EXPECTED_DEFAULT_DURATION, pageTiming.getOnLoad());
    }

    @Override
    void testMapping() {
        HarPageTiming pageTiming = map("{\"onContentLoad\": 1234, \"onLoad\": 5678, \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarPageTiming.class);

        assertEquals(1234, (int) pageTiming.getOnContentLoad());
        assertEquals(5678, (int) pageTiming.getOnLoad());
        assertEquals("My comment", pageTiming.getComment());

        assertNotNull(pageTiming.getAdditional());
        assertEquals("unknown", pageTiming.getAdditional().get("_unknown"));

        pageTiming = map(UNKNOWN_PROPERTY, HarPageTiming.class);
        assertNotNull(pageTiming);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarPageTiming.class).verify();
    }
}
