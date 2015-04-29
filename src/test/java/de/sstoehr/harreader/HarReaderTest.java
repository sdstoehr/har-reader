package de.sstoehr.harreader;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import de.sstoehr.harreader.model.Har;

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

    @Test(expected = HarReaderException.class)
    public void invalidDateStrict() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-date.har");
        Har har = HarReader.fromFile(harFile);
    }

    @Test
    public void invalidDateLax() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-date.har");
        Har har = HarReader.fromFile(harFile, HarReaderMode.LAX);
        Assert.assertNotNull(har);
    }
}
