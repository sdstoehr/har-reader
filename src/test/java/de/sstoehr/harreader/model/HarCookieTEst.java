package de.sstoehr.harreader.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class HarCookieTest extends AbstractMapperTest<HarCookie> {

    private final static Date EXPECTED_EXPIRES = new Date() {{
        setTime(1388577600000L);
    }};

    @Override
    public void testMapping() {
        HarCookie cookie = map("{\"name\":\"aName\",\"value\":\"aValue\",\"path\":\"/\",\"domain\":\"sstoehr.de\"," +
    "\"expires\":\"2014-01-01T12:00:00\",\"httpOnly\":\"true\",\"secure\":\"false\",\"comment\":\"my comment\"}", HarCookie.class);

        Assert.assertNotNull(cookie);
        Assert.assertEquals("aName", cookie.getName());
        Assert.assertEquals("aValue", cookie.getValue());
        Assert.assertEquals("/", cookie.getPath());
        Assert.assertEquals("sstoehr.de", cookie.getDomain());
        Assert.assertEquals(EXPECTED_EXPIRES, cookie.getExpires());
        Assert.assertEquals(true, cookie.getHttpOnly());
        Assert.assertEquals(false, cookie.getSecure());
        Assert.assertEquals("my comment", cookie.getComment());

        cookie = map(UNKNOWN_PROPERTY, HarCookie.class);
        Assert.assertNotNull(cookie);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameNull() {
        HarCookie cookie = new HarCookie();
        cookie.setName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueNull() {
        HarCookie cookie = new HarCookie();
        cookie.setValue(null);
    }
}
