package de.sstoehr.harreader.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class HarEntryTest extends AbstractMapperTest<HarEntry> {

    private final static Date EXPECTED_STARTED = new Date() {{
        setTime(1388577600000L);
    }};

    @Override
    public void testMapping() {
        HarEntry entry = map("{\"pageref\":\"aPageref\",\"startedDateTime\":\"2014-01-01T12:00:00\",\"time\":12345,"
        + "\"request\":{},\"response\":{},\"cache\":{},\"timings\":{},\"serverIPAddress\":\"1.2.3.4\",\"connection\":\"aConnection\","
        + "\"comment\":\"my comment\"}", HarEntry.class);

        Assert.assertNotNull(entry);
        Assert.assertEquals("aPageref", entry.getPageref());
        Assert.assertEquals(EXPECTED_STARTED, entry.getStartedDateTime());
        Assert.assertEquals(12345, (int) entry.getTime());
        Assert.assertNotNull(entry.getRequest());
        Assert.assertNotNull(entry.getResponse());
        Assert.assertNotNull(entry.getCache());
        Assert.assertNotNull(entry.getTimings());
        Assert.assertEquals("1.2.3.4", entry.getServerIPAddress());
        Assert.assertEquals("aConnection", entry.getConnection());
        Assert.assertEquals("my comment", entry.getComment());

        entry = map(UNKNOWN_PROPERTY, HarEntry.class);
        Assert.assertNotNull(entry);
    }

    @Test
    public void testStartedDateTimeCouldBeNull() {
        HarEntry entry = new HarEntry();
        entry.setStartedDateTime(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTimeNull() {
        HarEntry entry = new HarEntry();
        entry.setTime(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequestNull() {
        HarEntry entry = new HarEntry();
        entry.setRequest(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResponseNull() {
        HarEntry entry = new HarEntry();
        entry.setResponse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCacheNull() {
        HarEntry entry = new HarEntry();
        entry.setCache(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTimingsNull() {
        HarEntry entry = new HarEntry();
        entry.setTimings(null);
    }
}
