package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarPostDataParamTest extends AbstractMapperTest<HarPostDataParam> {

    @Override
    void testMapping() {
        HarPostDataParam postDataParam = map("{\"name\": \"aName\", \"value\": \"aValue\", \"fileName\": \"aFilename\", \"contentType\": \"aContentType\", \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarPostDataParam.class);

        assertEquals("aName", postDataParam.getName());
        assertEquals("aValue", postDataParam.getValue());
        assertEquals("aFilename", postDataParam.getFileName());
        assertEquals("aContentType", postDataParam.getContentType());
        assertEquals("My comment", postDataParam.getComment());

        assertNotNull(postDataParam.getAdditional());
        assertEquals("unknown", postDataParam.getAdditional().get("_unknown"));

        postDataParam = map(UNKNOWN_PROPERTY, HarPostDataParam.class);
        assertNotNull(postDataParam);
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarPostDataParam.class).verify();
    }

}