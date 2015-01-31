package de.sstoehr.harreader.model;

import org.junit.Assert;
import org.junit.Test;

public class HttpStatusTest {

    @Test
    public void test302() {
        Assert.assertEquals(HttpStatus.FOUND, HttpStatus.getByCode(302));
        Assert.assertEquals(HttpStatus.FOUND, HttpStatus.getByText("fOUnd"));
        Assert.assertEquals(HttpStatus.FOUND, HttpStatus.getByText("Moved temporarily"));
        Assert.assertEquals(HttpStatus.FOUND, HttpStatus.getByText("Object moved"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCode() {
        HttpStatus.getByCode(1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidText() {
        HttpStatus.getByText("Not a HTTP status");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTextNull() {
        HttpStatus.getByText(null);
    }
}
