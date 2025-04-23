package de.sstoehr.harreader.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.*;

class HarTimingTest extends AbstractMapperTest<HarTiming> {

    @Override
    @Test
    void testMapping() {
        HarTiming timing = map("{\"blocked\": 3804,\"dns\": 23,\"connect\": 5,\"send\": 9,\"wait\": 5209,"
        + "\"receive\": 63880931727107, \"ssl\": 123, \"comment\": \"my comment\",\"_unknown\":\"unknown\"}", HarTiming.class);

        assertNotNull(timing);
        assertEquals(3804, timing.blocked());
        assertEquals(23, timing.dns());
        assertEquals(5, timing.connect());
        assertEquals(9, timing.send());
        assertEquals(5209, timing.waitTime());
        assertEquals(63880931727107L, timing.receive());
        assertEquals(123, timing.ssl());
        assertEquals("my comment", timing.comment());

        assertNotNull(timing.additional());
        assertEquals("unknown", timing.additional().get("_unknown"));
    }

    @Test
    void testBlocked() {
        HarTiming timing = new HarTiming();
        assertEquals(-1, timing.blocked());
    }

    @Test
    void testDns() {
        HarTiming timing = new HarTiming();
        assertEquals(-1, timing.dns());
    }

    @Test
    void testConnect() {
        HarTiming timing = new HarTiming();
        assertEquals(-1, timing.connect());
    }

    @Test
    void testSsl() {
        HarTiming timing = new HarTiming();
        assertEquals(-1, timing.ssl());
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
    void testBuilder() {
        HarTiming timing = HarTiming.builder().build();
        testNullability(timing);

        timing = HarTiming.builder()
                .blocked(3804L)
                .dns(23L)
                .connect(5L)
                .send(9L)
                .waitTime(5209L)
                .receive(79L)
                .ssl(123L)
                .comment("my comment")
                .build();

        assertEquals(3804, timing.blocked());
        assertEquals(23, timing.dns());
        assertEquals(5, timing.connect());
        assertEquals(9, timing.send());
        assertEquals(5209, timing.waitTime());
        assertEquals(79, timing.receive());
        assertEquals(123, timing.ssl());
        assertEquals("my comment", timing.comment());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarTiming.class).verify();
    }
}