package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarCreatorBrowserTest extends AbstractMapperTest<HarCreatorBrowser> {

    @Override
    public void testMapping() {
        HarCreatorBrowser creatorBrowser = map("{\"name\":\"aName\",\"version\":\"aVersion\",\"comment\":\"my comment\",\"_unknown\":\"unknown\"}", HarCreatorBrowser.class);

        assertNotNull(creatorBrowser);
        assertEquals("aName", creatorBrowser.getName());
        assertEquals("aVersion", creatorBrowser.getVersion());
        assertEquals("my comment", creatorBrowser.getComment());

        assertNotNull(creatorBrowser.getAdditional());
        assertEquals("unknown", creatorBrowser.getAdditional().get("_unknown"));

        creatorBrowser = map(UNKNOWN_PROPERTY, HarCreatorBrowser.class);
        assertNotNull(creatorBrowser);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarCreatorBrowser.class).verify();
    }

}
