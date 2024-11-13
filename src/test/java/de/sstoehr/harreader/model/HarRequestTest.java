package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class HarRequestTest extends AbstractMapperTest<HarRequest> {

    @Override
    void testMapping() {
        HarRequest request = map("{\"method\": \"GET\",\"url\": "
         + "\"http://www.sebastianstoehr.de/\",\"httpVersion\": "
         + "\"HTTP/1.1\",\"cookies\": [],\"headers\": [],\"queryString\": [],"
         + "\"headersSize\": 676,\"bodySize\": -1, \"postData\": {}, \"comment\":\"my comment\","
         + "\"_add\": \"additional info\"}", HarRequest.class);

        assertNotNull(request);
        assertEquals(HttpMethod.GET, request.getMethod());
        assertEquals("GET", request.getRawMethod());
        assertEquals("http://www.sebastianstoehr.de/", request.getUrl());
        assertEquals("HTTP/1.1", request.getHttpVersion());
        assertNotNull(request.getCookies());
        assertNotNull(request.getHeaders());
        assertNotNull(request.getQueryString());
        assertNotNull(request.getPostData());
        assertEquals(676L, (long) request.getHeadersSize());
        assertEquals(-1L, (long) request.getBodySize());
        assertEquals("my comment", request.getComment());
        assertEquals("additional info", request.getAdditional().get("_add"));
    }

    @Test
    void testCookies() {
        HarRequest request = new HarRequest();
        request.setCookies(null);
        assertNotNull(request.getCookies());
    }

    @Test
    void testHeaders() {
        HarRequest request = new HarRequest();
        request.setHeaders(null);
        assertNotNull(request.getHeaders());
    }

    @Test
    void testQueryString() {
        HarRequest request = new HarRequest();
        request.setQueryString(null);
        assertNotNull(request.getQueryString());
    }

    @Test
    void testPostData() {
        HarRequest request = new HarRequest();
        request.setPostData(null);
        assertNotNull(request.getPostData());
    }

    @Test
    void testHeadersSize() {
        HarRequest request = new HarRequest();
        request.setHeadersSize(null);
        assertEquals(-1L, (long) request.getHeadersSize());
    }

    @Test
    void testBodySize() {
        HarRequest request = new HarRequest();
        request.setBodySize(null);
        assertEquals(-1L, (long) request.getBodySize());
    }

    @Test
    void testUnknownMethod() {
        HarRequest request = new HarRequest();
        request.setRawMethod("NOT_PART_OF_ENUM");
        assertEquals("NOT_PART_OF_ENUM", request.getRawMethod());
        assertEquals(HttpMethod.UNKNOWN, request.getMethod());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarRequest.class).verify();
    }
}