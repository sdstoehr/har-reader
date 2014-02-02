package de.sstoehr.harreader.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HarLogTest extends AbstractMapperTest<HarLog> {

    private static final String EXPECTED_DEFAULT_VERSION = "1.1";
    private static final List<HarPage> EXPECTED_PAGES_LIST = new ArrayList<>();
    private static final List<HarEntry> EXPECTED_ENTRIES_LIST = new ArrayList<>();

    @Test
    public void testVersion() {
        HarLog log = new HarLog();
        Assert.assertEquals(EXPECTED_DEFAULT_VERSION, log.getVersion());

        log.setVersion("1.2");
        Assert.assertEquals("1.2", log.getVersion());

        log.setVersion(null);
        Assert.assertEquals(EXPECTED_DEFAULT_VERSION, log.getVersion());

        log.setVersion("");
        Assert.assertEquals(EXPECTED_DEFAULT_VERSION, log.getVersion());

        log.setVersion("  ");
        Assert.assertEquals(EXPECTED_DEFAULT_VERSION, log.getVersion());
    }

    @Test
    public void testPages() {
        HarLog log = new HarLog();
        Assert.assertEquals(EXPECTED_PAGES_LIST, log.getPages());

        log.setPages(null);
        Assert.assertEquals(EXPECTED_PAGES_LIST, log.getPages());
    }

    @Test
    public void testEntries() {
        HarLog log = new HarLog();
        Assert.assertEquals(EXPECTED_ENTRIES_LIST, log.getEntries());

        log.setEntries(null);
        Assert.assertEquals(EXPECTED_ENTRIES_LIST, log.getEntries());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatorNull() {
        HarLog log = new HarLog();
        log.setCreator(null);
    }

    @Override
    public void testMapping() {
        HarLog log = map("{\"creator\": {}, \"browser\": {}, \"comment\": \"My comment\"}", HarLog.class);

        Assert.assertEquals(EXPECTED_DEFAULT_VERSION, log.getVersion());
        Assert.assertNotNull(log.getCreator());
        Assert.assertNotNull(log.getBrowser());
        Assert.assertEquals(EXPECTED_PAGES_LIST, log.getPages());
        Assert.assertEquals(EXPECTED_ENTRIES_LIST, log.getEntries());
        Assert.assertEquals("My comment", log.getComment());

        log = map(UNKNOWN_PROPERTY, HarLog.class);
        Assert.assertNotNull(log);
    }
}
