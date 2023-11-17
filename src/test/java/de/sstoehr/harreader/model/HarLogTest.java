package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class HarLogTest extends AbstractMapperTest<HarLog> {

    private static final String EXPECTED_DEFAULT_VERSION = "1.1";
    private static final List<HarPage> EXPECTED_PAGES_LIST = new ArrayList<>();
    private static final List<HarEntry> EXPECTED_ENTRIES_LIST = new ArrayList<>();

    @Test
    void testVersion() {
        HarLog log = new HarLog();
        assertEquals(EXPECTED_DEFAULT_VERSION, log.getVersion());

        log.setVersion("1.2");
        assertEquals("1.2", log.getVersion());

        log.setVersion(null);
        assertEquals(EXPECTED_DEFAULT_VERSION, log.getVersion());

        log.setVersion("");
        assertEquals(EXPECTED_DEFAULT_VERSION, log.getVersion());

        log.setVersion("  ");
        assertEquals(EXPECTED_DEFAULT_VERSION, log.getVersion());
    }

    @Test
    void testPages() {
        HarLog log = new HarLog();
        assertEquals(EXPECTED_PAGES_LIST, log.getPages());

        log.setPages(null);
        assertEquals(EXPECTED_PAGES_LIST, log.getPages());
    }

    @Test
    void testEntries() {
        HarLog log = new HarLog();
        assertEquals(EXPECTED_ENTRIES_LIST, log.getEntries());

        log.setEntries(null);
        assertEquals(EXPECTED_ENTRIES_LIST, log.getEntries());
    }

    @Test
    void testCreatorNull() {
        HarLog log = new HarLog();
        log.setCreator(null);
        assertNotNull(log.getCreator());
    }

    @Test
    void testBrowserNull() {
        HarLog log = new HarLog();
        log.setBrowser(null);
        assertNotNull(log.getBrowser());
    }

    @Override
    void testMapping() {
        HarLog log = map("{\"creator\": {}, \"browser\": {}, \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarLog.class);

        assertEquals(EXPECTED_DEFAULT_VERSION, log.getVersion());
        assertNotNull(log.getCreator());
        assertNotNull(log.getBrowser());
        assertEquals(EXPECTED_PAGES_LIST, log.getPages());
        assertEquals(EXPECTED_ENTRIES_LIST, log.getEntries());
        assertEquals("My comment", log.getComment());

        assertNotNull(log.getAdditional());
        assertEquals("unknown", log.getAdditional().get("_unknown"));

        log = map(UNKNOWN_PROPERTY, HarLog.class);
        assertNotNull(log);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarLog.class).verify();
    }
}
