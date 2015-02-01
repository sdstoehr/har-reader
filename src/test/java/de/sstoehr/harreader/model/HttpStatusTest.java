package de.sstoehr.harreader.model;

import org.junit.Assert;
import org.junit.Test;

public class HttpStatusTest {

    @Test
    public void test302() {
        Assert.assertEquals(HttpStatus.FOUND, HttpStatus.byCode(302));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCode() {
        HttpStatus.byCode(1000);
    }

}
