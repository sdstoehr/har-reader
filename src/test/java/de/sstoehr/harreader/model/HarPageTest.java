package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

class HarPageTest extends AbstractMapperTest<HarPage> {

    private final static Date EXPECTED_STARTED = new Date() {{
        setTime(1388577600000L);
    }};

    @Override
    void testMapping() {
        HarPage page = map("{\"startedDateTime\":\"2014-01-01T12:00:00\",\"id\":\"anId\","
        + "\"title\":\"aTitle\",\"pageTimings\":{},\"comment\":\"my comment\", \"_add\": \"additional info\"}", HarPage.class);

        assertNotNull(page);
        assertEquals(EXPECTED_STARTED, page.getStartedDateTime());
        assertEquals("anId", page.getId());
        assertEquals("aTitle", page.getTitle());
        assertNotNull(page.getPageTimings());
        assertEquals("my comment", page.getComment());
        assertEquals("additional info", page.getAdditional().get("_add"));

        page = map(UNKNOWN_PROPERTY, HarPage.class);
        assertNotNull(page);
    }

    @Test
    void testPageTimingsNull() {
        HarPage page = new HarPage();
        page.setPageTimings(null);
        assertNotNull(page.getPageTimings());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarPage.class).verify();
    }
}
