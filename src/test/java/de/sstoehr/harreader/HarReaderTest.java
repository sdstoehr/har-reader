package de.sstoehr.harreader;

import de.sstoehr.harreader.model.Har;
import org.hamcrest.CoreMatchers;
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

    @Test
    public void invalidDateStrict() {
        File harFile = new File("src/test/resources/sstoehr.invalid-date.har");
        try {
            HarReader.fromFile(harFile);
            Assert.fail("Expected exception " + HarReaderException.class + " not found");
        } catch (HarReaderException e) {
            Assert.assertThat(e.getMessage(),
                CoreMatchers.containsString("Tried default date formats of jackson"));
        }
    }

    @Test
    public void invalidDateLax() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-date.har");
        Har har = HarReader.fromFile(harFile, HarReaderMode.LAX);
        Assert.assertNotNull(har);
    }
}
