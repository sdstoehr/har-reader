package de.sstoehr.harreader;

import de.sstoehr.harreader.model.Har;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class HarReaderTest {

    private HarReader harReader = new HarReader();

    @Test
    public void test() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.har");
        Har har = harReader.readFromFile(harFile);
        Assert.assertNotNull(har);
    }

    @Test
    public void missingLog() throws HarReaderException {
        Har har = harReader.readFromString("{\"unknown\":\"!\"}");
        Assert.assertNotNull(har);
    }

    @Test(expected = HarReaderException.class)
    public void invalidDateStrict() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-date.har");
        harReader.readFromFile(harFile);
    }

    @Test
    public void invalidDateLax() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-date.har");
        Har har = harReader.readFromFile(harFile, HarReaderMode.LAX);
        Assert.assertNotNull(har);
    }

    @Test(expected = HarReaderException.class)
    public void invalidIntegerStrict() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-integer.har");
        harReader.readFromFile(harFile);
    }

    @Test
    public void invalidIntegerLax() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-integer.har");
        Har har = harReader.readFromFile(harFile, HarReaderMode.LAX);
        Assert.assertNotNull(har);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mapperFactoryNotNull() {
        new HarReader(null);
    }
}
