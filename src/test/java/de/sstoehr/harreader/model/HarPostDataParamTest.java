package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarPostDataParamTest extends AbstractMapperTest<HarPostDataParam> {

    @Override
    @Test
    void testMapping() {
        HarPostDataParam postDataParam = map("{\"name\": \"aName\", \"value\": \"aValue\", \"fileName\": \"aFilename\", \"contentType\": \"aContentType\", \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarPostDataParam.class);

        assertEquals("aName", postDataParam.name());
        assertEquals("aValue", postDataParam.value());
        assertEquals("aFilename", postDataParam.fileName());
        assertEquals("aContentType", postDataParam.contentType());
        assertEquals("My comment", postDataParam.comment());

        assertNotNull(postDataParam.additional());
        assertEquals("unknown", postDataParam.additional().get("_unknown"));

        postDataParam = map(UNKNOWN_PROPERTY, HarPostDataParam.class);
        assertNotNull(postDataParam);
    }

    @Test
    void testNullability() {
        testNullability(new HarPostDataParam());
    }

    @Test
    void testBuilder() {
        HarPostDataParam postDataParam = HarPostDataParam.builder().build();
        testNullability(postDataParam);

        postDataParam = HarPostDataParam.builder()
                .name("aName")
                .value("aValue")
                .fileName("aFilename")
                .contentType("aContentType")
                .comment("My comment")
                .build();

        assertEquals("aName", postDataParam.name());
        assertEquals("aValue", postDataParam.value());
        assertEquals("aFilename", postDataParam.fileName());
        assertEquals("aContentType", postDataParam.contentType());
        assertEquals("My comment", postDataParam.comment());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarPostDataParam.class).verify();
    }

}