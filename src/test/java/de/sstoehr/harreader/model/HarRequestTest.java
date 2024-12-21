package de.sstoehr.harreader.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.*;

class HarRequestTest extends AbstractMapperTest<HarRequest> {

    @Override
    @Test
    void testMapping() {
        HarRequest request = map("{\"method\": \"GET\",\"url\": "
         + "\"http://www.sebastianstoehr.de/\",\"httpVersion\": "
         + "\"HTTP/1.1\",\"cookies\": [],\"headers\": [],\"queryString\": [],"
         + "\"headersSize\": 676,\"bodySize\": -1, \"postData\": {}, \"comment\":\"my comment\","
         + "\"_add\": \"additional info\"}", HarRequest.class);

        assertNotNull(request);
        assertEquals(HttpMethod.GET, request.httpMethod());
        assertEquals("GET", request.method());
        assertEquals("http://www.sebastianstoehr.de/", request.url());
        assertEquals("HTTP/1.1", request.httpVersion());
        assertNotNull(request.cookies());
        assertNotNull(request.headers());
        assertNotNull(request.queryString());
        assertNotNull(request.postData());
        assertEquals(676L, (long) request.headersSize());
        assertEquals(-1L, (long) request.bodySize());
        assertEquals("my comment", request.comment());
        assertEquals("additional info", request.additional().get("_add"));
    }

    @Test
    void testCookies() {
        HarRequest request = new HarRequest();
        assertNotNull(request.cookies());
    }

    @Test
    void testHeaders() {
        HarRequest request = new HarRequest();
        assertNotNull(request.headers());
    }

    @Test
    void testQueryString() {
        HarRequest request = new HarRequest();
        assertNotNull(request.queryString());
    }

    @Test
    void testPostData() {
        HarRequest request = new HarRequest();
        assertNotNull(request.postData());
    }

    @Test
    void testHeadersSize() {
        HarRequest request = new HarRequest();
        assertEquals(-1L, (long) request.headersSize());
    }

    @Test
    void testBodySize() {
        HarRequest request = new HarRequest();
        assertEquals(-1L, (long) request.bodySize());
    }

    @Test
    void testUnknownMethod() {
        HarRequest request = new HarRequest("POST", null, null, null, null, null, null, null,
                null, null, null);
        assertEquals("POST", request.method());
        assertEquals(HttpMethod.POST, request.httpMethod());
    }

    @Test
    void testUnknownMethodRaw() {
        HarRequest request = new HarRequest("NOT_PART_OF_ENUM", null, null, null, null, null, null, null,
                null, null, null);
        assertEquals("NOT_PART_OF_ENUM", request.method());
        assertEquals(HttpMethod.UNKNOWN, request.httpMethod());
    }

    @Test
    void testNullMethodRaw() {
        HarRequest request = new HarRequest(null, null, null, null, null, null, null, null,
                null, null, null);
        assertNull(request.method());
        assertEquals(HttpMethod.UNKNOWN, request.httpMethod());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarRequest.class).verify();
    }
}