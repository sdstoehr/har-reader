package de.sstoehr.harreader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.sstoehr.harreader.jackson.DefaultMapperFactory;
import org.junit.jupiter.api.Test;

import de.sstoehr.harreader.model.Har;

class HarWriterTests {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
    private static final File HAR_FILE = new File("src/test/resources/sstoehr.har");

    @Test
    void shouldWriteHarAsString() throws IOException {
        Har expected = MAPPER.readValue(HAR_FILE, Har.class);

        Har har = new HarReader().readFromFile(HAR_FILE);
        HarWriter writer = new HarWriter();
        assertEquals(MAPPER.writeValueAsString(expected), writer.writeAsString(har));
    }

    @Test
    void shouldWriteHarAsBytes() throws IOException {
        Har expected = MAPPER.readValue(HAR_FILE, Har.class);

        Har har = new HarReader().readFromFile(HAR_FILE);
        HarWriter writer = new HarWriter(new DefaultMapperFactory());
        assertEquals(MAPPER.writeValueAsString(expected), new String(writer.writeAsBytes(har), StandardCharsets.UTF_8));
    }

    @Test
    void testWriteToOutputStream() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Har expected = MAPPER.readValue(HAR_FILE, Har.class);

        HarWriter writer = new HarWriter();
        writer.writeTo(baos, expected);
        assertEquals(MAPPER.writeValueAsString(expected), new String(baos.toByteArray(), StandardCharsets.UTF_8));
    }

    @Test
    void testWriteToFile() throws IOException {
        File file = File.createTempFile("pref", "suff");
        Har expected = MAPPER.readValue(HAR_FILE, Har.class);

        HarWriter writer = new HarWriter();
        writer.writeTo(file, expected);
        Har actual = MAPPER.readValue(file, Har.class);
        assertEquals(MAPPER.writeValueAsString(expected), MAPPER.writeValueAsString(actual));
    }

    @Test
    void testWriteToWriter() throws IOException {
        StringWriter sw = new StringWriter();
        Har expected = MAPPER.readValue(HAR_FILE, Har.class);

        HarWriter writer = new HarWriter();
        writer.writeTo(sw, expected);
        assertEquals(MAPPER.writeValueAsString(expected), sw.toString());
    }

}
