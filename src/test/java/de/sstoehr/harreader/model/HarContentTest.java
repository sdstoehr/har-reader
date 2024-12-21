package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarContentTest extends AbstractMapperTest<HarContent> {

    @Override
    @Test
    public void testMapping() {
        HarContent content = map("{\"size\":123,\"compression\":45,\"mimeType\":\"mime/type\"," +
        "\"text\":\"my content\",\"encoding\":\"base64\",\"comment\":\"my comment\",\"_unknown\":\"unknown\"}", HarContent.class);

        assertEquals(123L, (long) content.size());
        assertEquals(45L, (long) content.compression());
        assertEquals("mime/type", content.mimeType());
        assertEquals("my content", content.text());
        assertEquals("base64", content.encoding());
        assertEquals("my comment", content.comment());

        assertNotNull(content.additional());
        assertEquals("unknown", content.additional().get("_unknown"));

        content = map(UNKNOWN_PROPERTY, HarContent.class);
        assertNotNull(content);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarContent.class).verify();
    }

}
