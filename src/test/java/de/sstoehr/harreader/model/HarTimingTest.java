package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarTimingTest extends AbstractMapperTest<HarTiming> {

    @Override
    void testMapping() {
        HarTiming timing = map("{\"blocked\": 3804,\"dns\": 23,\"connect\": 5,\"send\": 9,\"wait\": 5209,"
        + "\"receive\": 79, \"ssl\": 123, \"comment\": \"my comment\",\"_unknown\":\"unknown\"}", HarTiming.class);

        assertNotNull(timing);
        assertEquals(3804, (int) timing.getBlocked());
        assertEquals(23, (int) timing.getDns());
        assertEquals(5, (int) timing.getConnect());
        assertEquals(9, (int) timing.getSend());
        assertEquals(5209, (int) timing.getWait());
        assertEquals(79, (int) timing.getReceive());
        assertEquals(123, (int) timing.getSsl());
        assertEquals("my comment", timing.getComment());

        assertNotNull(timing.getAdditional());
        assertEquals("unknown", timing.getAdditional().get("_unknown"));
    }

    @Test
    void testBlocked() {
        HarTiming timing = new HarTiming();
        timing.setBlocked(null);
        assertEquals(-1, (int) timing.getBlocked());
    }

    @Test
    void testDns() {
        HarTiming timing = new HarTiming();
        timing.setDns(null);
        assertEquals(-1, (int) timing.getDns());
    }

    @Test
    void testConnect() {
        HarTiming timing = new HarTiming();
        timing.setConnect(null);
        assertEquals(-1, (int) timing.getConnect());
    }

    @Test
    void testSsl() {
        HarTiming timing = new HarTiming();
        timing.setSsl(null);
        assertEquals(-1, (int) timing.getSsl());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarTiming.class).verify();
    }
}