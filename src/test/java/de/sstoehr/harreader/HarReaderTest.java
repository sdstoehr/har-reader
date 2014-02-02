package de.sstoehr.harreader;

import de.sstoehr.harreader.model.Har;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class HarReaderTest {

    @Test
    public void test() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.har");
        Har har = HarReader.fromFile(harFile);
        Assert.assertNotNull(har);
    }

    @Test
    public void missingLog() throws HarReaderException {
        Har har = HarReader.fromString("{\"unknown\":\"!\"}");
        Assert.assertNotNull(har);
    }
}
