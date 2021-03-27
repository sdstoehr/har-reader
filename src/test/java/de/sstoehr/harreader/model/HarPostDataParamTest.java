package de.sstoehr.harreader.model;

import org.junit.Assert;

public class HarPostDataParamTest extends AbstractMapperTest<HarPostDataParam> {

    @Override
    public void testMapping() {
        HarPostDataParam postDataParam = map("{\"name\": \"aName\", \"value\": \"aValue\", \"fileName\": \"aFilename\", \"contentType\": \"aContentType\", \"comment\": \"My comment\",\"_unknown\":\"unknown\"}", HarPostDataParam.class);

        Assert.assertEquals("aName", postDataParam.getName());
        Assert.assertEquals("aValue", postDataParam.getValue());
        Assert.assertEquals("aFilename", postDataParam.getFileName());
        Assert.assertEquals("aContentType", postDataParam.getContentType());
        Assert.assertEquals("My comment", postDataParam.getComment());

        Assert.assertNotNull(postDataParam.getAdditional());
        Assert.assertEquals("unknown", postDataParam.getAdditional().get("_unknown"));

        postDataParam = map(UNKNOWN_PROPERTY, HarPostDataParam.class);
        Assert.assertNotNull(postDataParam);
    }

}