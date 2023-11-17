package de.sstoehr.harreader;

import de.sstoehr.harreader.model.Har;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

class HarReaderTest {

    private HarReader harReader = new HarReader();

    @Test
    void test() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.har");
        Har har = harReader.readFromFile(harFile);
        assertNotNull(har);
    }

    @Test
    void missingLog() throws HarReaderException {
        Har har = harReader.readFromString("{\"unknown\":\"!\"}");
        assertNotNull(har);
    }

    @Test
    void invalidDateStrict() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-date.har");
        assertThrows(HarReaderException.class, () -> harReader.readFromFile(harFile));
    }

    @Test
    void invalidDateLax() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-date.har");
        Har har = harReader.readFromFile(harFile, HarReaderMode.LAX);
        assertNotNull(har);
    }

    @Test
    void invalidIntegerStrict() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-integer.har");
        assertThrows(HarReaderException.class, () -> harReader.readFromFile(harFile));
    }

    @Test
    void invalidIntegerLax() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.invalid-integer.har");
        Har har = harReader.readFromFile(harFile, HarReaderMode.LAX);
        assertNotNull(har);
    }

    @Test
    void mapperFactoryNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new HarReader(null));
    }

    @Test
    void testEquals() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.har");
        Har har1 = harReader.readFromFile(harFile);
        Har har2 = harReader.readFromFile(harFile);
        assertTrue(har1.equals(har2));
    }

    @Test
    void testHashCode() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.har");
        Har har1 = harReader.readFromFile(harFile);
        Har har2 = harReader.readFromFile(harFile);
        assertEquals(har1.hashCode(), har2.hashCode());
    }
}
