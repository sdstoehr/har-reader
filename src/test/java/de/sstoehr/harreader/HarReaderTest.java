package de.sstoehr.harreader;

import de.sstoehr.harreader.model.Har;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

class HarReaderTest {

    private static final String PATH_TO_VALID_HAR = "src/test/resources/sstoehr.har";

    private HarReader harReader = new HarReader();

    @Test
    void shouldReadFromFile() throws HarReaderException {
        File harFile = new File(PATH_TO_VALID_HAR);
        Har har = harReader.readFromFile(harFile);
        assertNotNull(har);
    }

    @Test
    void shouldReadFromInputStream() throws IOException {
        File harFile = new File(PATH_TO_VALID_HAR);
        InputStream inputStream = Files.newInputStream(harFile.toPath());
        Har har = harReader.readFromInputStream(inputStream);
        assertNotNull(har);
    }

    @Test
    void shouldReadFromString() throws IOException {
        byte[] bytes = Files.readAllBytes(new File(PATH_TO_VALID_HAR).toPath());
        String harAsString = new String(bytes, StandardCharsets.UTF_8);
        Har har = harReader.readFromString(harAsString);
        assertNotNull(har);
    }

    @Test
    void shouldReadFromBytes() throws IOException {
        byte[] harAsBytes = Files.readAllBytes(new File(PATH_TO_VALID_HAR).toPath());
        Har har = harReader.readFromBytes(harAsBytes);
        assertNotNull(har);
    }

    @Test
    void missingLog() throws HarReaderException {
        Har har = harReader.readFromString("{\"unknown\":\"!\"}");
        assertNotNull(har);
    }

    @Test
    void invalidDateStrict() {
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
        File harFile = new File("src/test/resources/sstoehr.long-timing.har");
        Har har = harReader.readFromFile(harFile, HarReaderMode.STRICT);
        assertNotNull(har);
    }

    @Test
    void invalidIntegerLax() throws HarReaderException {
        File harFile = new File("src/test/resources/sstoehr.long-timing.har");
        Har har = harReader.readFromFile(harFile, HarReaderMode.LAX);
        assertNotNull(har);
    }

    @Test
    void mapperFactoryNotNull() {
        assertThrows(IllegalArgumentException.class, () -> new HarReader(null));
    }

    @Test
    void testEquals() throws HarReaderException {
        File harFile = new File(PATH_TO_VALID_HAR);
        Har har1 = harReader.readFromFile(harFile);
        Har har2 = harReader.readFromFile(harFile);
        assertTrue(har1.equals(har2));
    }

    @Test
    void testHashCode() throws HarReaderException {
        File harFile = new File(PATH_TO_VALID_HAR);
        Har har1 = harReader.readFromFile(harFile);
        Har har2 = harReader.readFromFile(harFile);
        assertEquals(har1.hashCode(), har2.hashCode());
    }
}
