package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

public class HarQueryParamTest extends AbstractMapperTest<HarQueryParam> {

    @Override
    public void testMapping() {
        HarQueryParam queryParam = map("{\"name\": \"aName\", \"value\":\"aValue\", \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarQueryParam.class);

        Assert.assertEquals("aName", queryParam.getName());
        Assert.assertEquals("aValue", queryParam.getValue());
        Assert.assertEquals("My comment", queryParam.getComment());

        Assert.assertNotNull(queryParam.getAdditional());
        Assert.assertEquals("unknown", queryParam.getAdditional().get("_unknown"));
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.simple().forClass(HarQueryParam.class).verify();
    }
}