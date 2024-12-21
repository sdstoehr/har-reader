package de.sstoehr.harreader.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.*;

class HarTimingTest extends AbstractMapperTest<HarTiming> {

    @Override
    @Test
    void testMapping() {
        HarTiming timing = map("{\"blocked\": 3804,\"dns\": 23,\"connect\": 5,\"send\": 9,\"wait\": 5209,"
        + "\"receive\": 79, \"ssl\": 123, \"comment\": \"my comment\",\"_unknown\":\"unknown\"}", HarTiming.class);

        assertNotNull(timing);
        assertEquals(3804, (int) timing.blocked());
        assertEquals(23, (int) timing.dns());
        assertEquals(5, (int) timing.connect());
        assertEquals(9, (int) timing.send());
        assertEquals(5209, (int) timing.waitTime());
        assertEquals(79, (int) timing.receive());
        assertEquals(123, (int) timing.ssl());
        assertEquals("my comment", timing.comment());

        assertNotNull(timing.additional());
        assertEquals("unknown", timing.additional().get("_unknown"));
    }

    @Test
    void testBlocked() {
        HarTiming timing = new HarTiming();
        assertEquals(-1, (int) timing.blocked());
    }

    @Test
    void testDns() {
        HarTiming timing = new HarTiming();
        assertEquals(-1, (int) timing.dns());
    }

    @Test
    void testConnect() {
        HarTiming timing = new HarTiming();
        assertEquals(-1, (int) timing.connect());
    }

    @Test
    void testSsl() {
        HarTiming timing = new HarTiming();
        assertEquals(-1, (int) timing.ssl());
    }

    @Test
    void testSend() {
        HarTiming timing = new HarTiming();
        assertNull(timing.send());
    }

    @Test
    void testWait() {
        HarTiming timing = new HarTiming();
        assertNull(timing.waitTime());
    }

    @Test
    void testReceive() {
        HarTiming timing = new HarTiming();
        assertNull(timing.receive());
    }

    @Test
    void testNullability() {
        testNullability(new HarTiming());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarTiming.class).verify();
    }
}