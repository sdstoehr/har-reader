package de.sstoehr.harreader.model;

import org.junit.Assert;
import org.junit.Test;

public class HarContentTest extends AbstractMapperTest<HarContent> {

    @Override
    public void testMapping() {
        HarContent content = map("{\"size\":123,\"compression\":45,\"mimeType\":\"mime/type\"," +
        "\"text\":\"my content\",\"encoding\":\"base64\",\"comment\":\"my comment\"}", HarContent.class);

        Assert.assertEquals(123L, (long) content.getSize());
        Assert.assertEquals(45L, (long) content.getCompression());
        Assert.assertEquals("mime/type", content.getMimeType());
        Assert.assertEquals("my content", content.getText());
        Assert.assertEquals("base64", content.getEncoding());
        Assert.assertEquals("my comment", content.getComment());

        content = map(UNKNOWN_PROPERTY, HarContent.class);
        Assert.assertNotNull(content);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSizeNull() {
        HarContent content = new HarContent();
        content.setSize(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMimeTypeNull() {
        HarContent content = new HarContent();
        content.setMimeType(null);
    }
}
