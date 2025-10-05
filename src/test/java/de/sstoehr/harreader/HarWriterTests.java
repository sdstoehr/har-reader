package de.sstoehr.harreader;

import de.sstoehr.harreader.jackson.DefaultMapperFactory;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarCreatorBrowser;
import de.sstoehr.harreader.model.HarEntry;
import de.sstoehr.harreader.model.HarLog;
import de.sstoehr.harreader.model.HarRequest;
import de.sstoehr.harreader.model.HarResponse;
import de.sstoehr.harreader.model.HttpMethod;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.time.ZonedDateTime;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HarWriterTests {
    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .configure(DateTimeFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false).build();
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
        assertEquals(MAPPER.writeValueAsString(expected), new String(writer.writeAsBytes(har), UTF_8));
    }

    @Test
    void testWriteToOutputStream() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Har expected = MAPPER.readValue(HAR_FILE, Har.class);

        HarWriter writer = new HarWriter();
        writer.writeTo(baos, expected);
        assertEquals(MAPPER.writeValueAsString(expected), baos.toString(UTF_8));
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

    @Test
    void testWriteFromBuilder() throws IOException {
        Har har = Har.builder()
                .log(HarLog.builder()
                        .creator(HarCreatorBrowser.builder()
                                .name("HAR reader")
                                .version("1.0")
                                .build())
                        .entry(HarEntry.builder()
                                .pageref("page_0")
                                .startedDateTime(ZonedDateTime.parse("2021-01-01T00:00:00Z"))
                                .time(42)
                                .request(HarRequest.builder()
                                        .method(HttpMethod.GET.name())
                                        .url("https://www.example.com")
                                        .httpVersion("HTTP/1.1")
                                        .build())
                                .response(HarResponse.builder()
                                        .status(200)
                                        .statusText("OK")
                                        .httpVersion("HTTP/1.1")
                                        .build())
                                .build())
                        .build()
                ).build();

        HarWriter writer = new HarWriter();
        assertEquals("{\"log\":{\"version\":\"1.1\",\"creator\":{\"name\":\"HAR reader\",\"version\":\"1.0\"},\"pages\":[],\"entries\":[{\"pageref\":\"page_0\",\"startedDateTime\":\"2021-01-01T00:00:00Z\",\"time\":42,\"request\":{\"method\":\"GET\",\"url\":\"https://www.example.com\",\"httpVersion\":\"HTTP/1.1\",\"cookies\":[],\"headers\":[],\"queryString\":[],\"postData\":{\"params\":[]},\"headersSize\":-1,\"bodySize\":-1},\"response\":{\"status\":200,\"statusText\":\"OK\",\"httpVersion\":\"HTTP/1.1\",\"cookies\":[],\"headers\":[],\"content\":{},\"headersSize\":-1,\"bodySize\":-1},\"cache\":{},\"timings\":{\"blocked\":-1,\"dns\":-1,\"connect\":-1,\"ssl\":-1}}]}}",
                writer.writeAsString(har));
    }

}
