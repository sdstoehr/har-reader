package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HarPostDataTest extends AbstractMapperTest<HarPostData> {

    private static final List<HarPostDataParam> EXPECTED_LIST = new ArrayList<>();

    @Override
    public void testMapping() {
        HarPostData postData = map("{\"mimeType\": \"aMimeType\", \"params\": [], \"text\":\"aText\", \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarPostData.class);

        assertEquals("aMimeType", postData.getMimeType());
        assertEquals(EXPECTED_LIST, postData.getParams());
        assertEquals("aText", postData.getText());
        assertEquals("My comment", postData.getComment());

        assertNotNull(postData.getAdditional());
        assertEquals("unknown", postData.getAdditional().get("_unknown"));

        postData = map(UNKNOWN_PROPERTY, HarPostData.class);
        assertNotNull(postData);
    }

    @Test
    public void testParams() {
        HarPostData postData = new HarPostData();
        postData.setParams(null);
        assertNotNull(postData.getParams());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.simple().forClass(HarPostData.class).verify();
    }
}
