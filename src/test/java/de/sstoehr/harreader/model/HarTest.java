package de.sstoehr.harreader.model;

import org.junit.Assert;
import org.junit.Test;

public class HarTest extends AbstractMapperTest<Har>{

    @Test(expected = IllegalArgumentException.class)
    public void testCreatorNull() {
        Har har = new Har();
        har.setLog(null);
    }

    @Override
    public void testMapping() {
        Har har = map("{\"log\": {}}", Har.class);
        Assert.assertNotNull(har.getLog());

        har = map(UNKNOWN_PROPERTY, Har.class);
        Assert.assertNotNull(har);
    }

}
