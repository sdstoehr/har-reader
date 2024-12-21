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

        assertEquals("aMimeType", postData.mimeType());
        assertEquals(EXPECTED_LIST, postData.params());
        assertEquals("aText", postData.text());
        assertEquals("My comment", postData.comment());

        assertNotNull(postData.additional());
        assertEquals("unknown", postData.additional().get("_unknown"));

        postData = map(UNKNOWN_PROPERTY, HarPostData.class);
        assertNotNull(postData);
    }

    @Test
    public void testParams() {
        HarPostData postData = new HarPostData();
        assertNotNull(postData.params());
    }

    @Test
    void testNullability() {
        testNullability(new HarPostData());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.simple().forClass(HarPostData.class).verify();
    }
}
