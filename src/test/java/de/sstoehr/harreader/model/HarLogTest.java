package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertEquals(EXPECTED_DEFAULT_VERSION, log.version());

        log = new HarLog("1.2", null, null, null, null, null, null);
        assertEquals("1.2", log.version());

        log = new HarLog(null, null, null, null, null, null, null);
        assertEquals(EXPECTED_DEFAULT_VERSION, log.version());

        log = new HarLog("", null, null, null, null, null, null);
        assertEquals(EXPECTED_DEFAULT_VERSION, log.version());

        log = new HarLog("  ", null, null, null, null, null, null);
        assertEquals(EXPECTED_DEFAULT_VERSION, log.version());
    }

    @Test
    void testPages() {
        HarLog log = new HarLog();
        assertEquals(EXPECTED_PAGES_LIST, log.pages());

        log = new HarLog("1.2", null, null, null, null, null, null);
        assertEquals(EXPECTED_PAGES_LIST, log.pages());
    }

    @Test
    void testEntries() {
        HarLog log = new HarLog();
        assertEquals(EXPECTED_ENTRIES_LIST, log.entries());

        log = new HarLog("1.2", null, null, null, null, null, null);
        assertEquals(EXPECTED_ENTRIES_LIST, log.entries());
    }

    @Test
    void testCreatorNull() {
        HarLog log = new HarLog();
        assertNotNull(log.creator());
    }

    @Test
    void testBrowserNull() {
        HarLog log = new HarLog();
        assertNull(log.browser());
    }

    @Override
    @Test
    void testMapping() {
        HarLog log = map("{\"creator\": {}, \"browser\": {}, \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarLog.class);

        assertEquals(EXPECTED_DEFAULT_VERSION, log.version());
        assertNotNull(log.creator());
        assertNotNull(log.browser());
        assertEquals(EXPECTED_PAGES_LIST, log.pages());
        assertEquals(EXPECTED_ENTRIES_LIST, log.entries());
        assertEquals("My comment", log.comment());

        assertNotNull(log.additional());
        assertEquals("unknown", log.additional().get("_unknown"));

        log = map(UNKNOWN_PROPERTY, HarLog.class);
        assertNotNull(log);
    }

    @Test
    void testNullability() {
        testNullability(new HarLog());
    }

    @Test
    void testBuilder() {
        HarLog log = HarLog.builder().build();
        testNullability(log);

        log = HarLog.builder()
                .version("1.2")
                .creator(HarCreatorBrowser.builder().build())
                .browser(HarCreatorBrowser.builder().build())
                .comment("My comment")
                .build();

        assertEquals("1.2", log.version());
        assertNotNull(log.creator());
        assertNotNull(log.browser());
        assertEquals("My comment", log.comment());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarLog.class).verify();
    }
}
