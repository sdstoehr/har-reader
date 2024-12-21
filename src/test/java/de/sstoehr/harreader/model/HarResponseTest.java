package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class HarResponseTest extends AbstractMapperTest<HarResponse> {

    @Override
    @Test
    void testMapping() {
        HarResponse response = map("{\"status\": 200,\"statusText\": \"OK\",\"httpVersion\": \"HTTP/1.1\","
        + "\"cookies\": [],\"headers\": [],\"content\": {},\"redirectURL\": \"redirectUrl\",\"headersSize\": 318,"
        + "\"bodySize\": 16997,\"comment\": \"My comment\", \"_add\": \"additional info\"}", HarResponse.class);

        assertNotNull(response);
        assertEquals(200, response.status());
        assertEquals("OK", response.statusText());
        assertEquals("HTTP/1.1", response.httpVersion());
        assertNotNull(response.cookies());
        assertNotNull(response.headers());
        assertNotNull(response.content());
        assertEquals("redirectUrl", response.redirectURL());
        assertEquals(318L, (long) response.headersSize());
        assertEquals(16997L, (long) response.bodySize());
        assertEquals("My comment", response.comment());
        assertEquals("additional info", response.additional().get("_add"));
    }

    @Test
    void testStatus() {
        HarResponse response = new HarResponse();
        assertEquals(0, response.status());
        assertEquals(HttpStatus.UNKNOWN_HTTP_STATUS, response.httpStatus());
    }

    @Test
    void testKnownStatus() {
        HarResponse response = new HarResponse(404, null, null, null, null, null, null, null, null, null, null);

        assertEquals(404, response.status());
        assertEquals(HttpStatus.NOT_FOUND, response.httpStatus());
    }

    @Test
    void testUnknownStatus() {
        HarResponse response = new HarResponse(600, null, null, null, null, null, null, null, null, null, null);

        assertEquals(600, response.status());
        assertEquals(HttpStatus.UNKNOWN_HTTP_STATUS, response.httpStatus());
    }

    @Test
    void testCookies() {
        HarResponse response = new HarResponse();
        assertNotNull(response.cookies());
    }

    @Test
    void testHeaders() {
        HarResponse response = new HarResponse();
        assertNotNull(response.headers());
    }

    @Test
    void testContent() {
        HarResponse response = new HarResponse();
        assertNotNull(response.content());
    }
    
    @Test
    void testHeadersSize() {
        HarResponse response = new HarResponse();
        assertEquals(-1L, (long) response.headersSize());
    }

    @Test
    void testBodySize() {
        HarResponse response = new HarResponse();
        assertEquals(-1L, (long) response.bodySize());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarResponse.class).verify();
    }
}