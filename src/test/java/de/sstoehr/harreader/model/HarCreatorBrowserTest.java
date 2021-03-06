package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

public class HarCreatorBrowserTest extends AbstractMapperTest<HarCreatorBrowser> {

    @Override
    public void testMapping() {
        HarCreatorBrowser creatorBrowser = map("{\"name\":\"aName\",\"version\":\"aVersion\",\"comment\":\"my comment\",\"_unknown\":\"unknown\"}", HarCreatorBrowser.class);

        Assert.assertNotNull(creatorBrowser);
        Assert.assertEquals("aName", creatorBrowser.getName());
        Assert.assertEquals("aVersion", creatorBrowser.getVersion());
        Assert.assertEquals("my comment", creatorBrowser.getComment());

        Assert.assertNotNull(creatorBrowser.getAdditional());
        Assert.assertEquals("unknown", creatorBrowser.getAdditional().get("_unknown"));

        creatorBrowser = map(UNKNOWN_PROPERTY, HarCreatorBrowser.class);
        Assert.assertNotNull(creatorBrowser);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.simple().forClass(HarCreatorBrowser.class).verify();
    }

}
