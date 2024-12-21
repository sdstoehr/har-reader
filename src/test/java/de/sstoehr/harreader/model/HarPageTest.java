package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

class HarPageTest extends AbstractMapperTest<HarPage> {

    private final static ZonedDateTime EXPECTED_STARTED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(1388577600000L), ZoneId.of("Z"));

    @Override
    @Test
    void testMapping() {
        HarPage page = map("{\"startedDateTime\":\"2014-01-01T12:00:00Z\",\"id\":\"anId\","
        + "\"title\":\"aTitle\",\"pageTimings\":{},\"comment\":\"my comment\", \"_add\": \"additional info\"}", HarPage.class);

        assertNotNull(page);
        assertEquals(EXPECTED_STARTED, page.startedDateTime());
        assertEquals("anId", page.id());
        assertEquals("aTitle", page.title());
        assertNotNull(page.pageTimings());
        assertEquals("my comment", page.comment());
        assertEquals("additional info", page.additional().get("_add"));

        page = map(UNKNOWN_PROPERTY, HarPage.class);
        assertNotNull(page);
    }

    @Test
    void testPageTimingsNull() {
        HarPage page = new HarPage();
        assertNotNull(page.pageTimings());
    }

    @Test
    void testNullability() {
        testNullability(new HarPage());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarPage.class).verify();
    }
}
