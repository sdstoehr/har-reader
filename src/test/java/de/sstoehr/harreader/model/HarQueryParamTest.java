package de.sstoehr.harreader.model;

import org.junit.Assert;
import org.junit.Test;

public class HarQueryParamTest extends AbstractMapperTest<HarQueryParam> {

    @Override
    public void testMapping() {
        HarQueryParam queryParam = map("{\"name\": \"aName\", \"value\":\"aValue\", \"comment\": \"My comment\"}", HarQueryParam.class);

        Assert.assertEquals("aName", queryParam.getName());
        Assert.assertEquals("aValue", queryParam.getValue());
        Assert.assertEquals("My comment", queryParam.getComment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameNull() {
        HarQueryParam queryParam = new HarQueryParam();
        queryParam.setName(null);
    }
}