package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarCreatorBrowserTest extends AbstractMapperTest<HarCreatorBrowser> {

    @Override
    @Test
    public void testMapping() {
        HarCreatorBrowser creatorBrowser = map("{\"name\":\"aName\",\"version\":\"aVersion\",\"comment\":\"my comment\",\"_unknown\":\"unknown\"}", HarCreatorBrowser.class);

        assertNotNull(creatorBrowser);
        assertEquals("aName", creatorBrowser.name());
        assertEquals("aVersion", creatorBrowser.version());
        assertEquals("my comment", creatorBrowser.comment());

        assertNotNull(creatorBrowser.additional());
        assertEquals("unknown", creatorBrowser.additional().get("_unknown"));

        creatorBrowser = map(UNKNOWN_PROPERTY, HarCreatorBrowser.class);
        assertNotNull(creatorBrowser);
    }

    @Test
    void testNullability() {
        testNullability(new HarCreatorBrowser());
    }

    @Test
    void testBuilder() {
        HarCreatorBrowser creatorBrowser = HarCreatorBrowser.builder().build();
        testNullability(creatorBrowser);

        creatorBrowser = HarCreatorBrowser.builder()
                .name("aName")
                .version("aVersion")
                .comment("my comment")
                .build();

        assertEquals("aName", creatorBrowser.name());
        assertEquals("aVersion", creatorBrowser.version());
        assertEquals("my comment", creatorBrowser.comment());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarCreatorBrowser.class).verify();
    }

}
