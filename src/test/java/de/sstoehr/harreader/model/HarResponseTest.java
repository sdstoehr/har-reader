package de.sstoehr.harreader.model;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class HarResponseTest extends AbstractMapperTest<HarResponse> {

    @Override
    void testMapping() {
        HarResponse response = map("{\"status\": 200,\"statusText\": \"OK\",\"httpVersion\": \"HTTP/1.1\","
        + "\"cookies\": [],\"headers\": [],\"content\": {},\"redirectURL\": \"redirectUrl\",\"headersSize\": 318,"
        + "\"bodySize\": 16997,\"comment\": \"My comment\", \"_add\": \"additional info\"}", HarResponse.class);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals("OK", response.getStatusText());
        assertEquals("HTTP/1.1", response.getHttpVersion());
        assertNotNull(response.getCookies());
        assertNotNull(response.getHeaders());
        assertNotNull(response.getContent());
        assertEquals("redirectUrl", response.getRedirectURL());
        assertEquals(318L, (long) response.getHeadersSize());
        assertEquals(16997L, (long) response.getBodySize());
        assertEquals("My comment", response.getComment());
        assertEquals("additional info", response.getAdditional().get("_add"));
    }

    @Test
    void testStatus() {
        HarResponse response = new HarResponse();
        assertEquals(0, response.getStatus());
        assertEquals(0, response.getRawStatus());
    }

    @Test
    void testUnknownStatus() {
        HarResponse response = new HarResponse();
        response.setStatus(600);

        assertEquals(0, response.getStatus()); // old behaviour, falling back to UNKNOWN_STATUS_CODE
        assertEquals(600, response.getRawStatus());
    }

    @Test
    void testUnknownStatusRaw() {
        HarResponse response = new HarResponse();
        response.setRawStatus(600);

        assertEquals(0, response.getStatus()); // old behaviour, falling back to UNKNOWN_STATUS_CODE
        assertEquals(600, response.getRawStatus());
    }

    @Test
    void testCookies() {
        HarResponse response = new HarResponse();
        response.setCookies(null);
        assertNotNull(response.getCookies());
    }

    @Test
    void testHeaders() {
        HarResponse response = new HarResponse();
        response.setHeaders(null);
        assertNotNull(response.getHeaders());
    }

    @Test
    void testContent() {
        HarResponse response = new HarResponse();
        response.setContent(null);
        assertNotNull(response.getContent());
    }
    
    @Test
    void testHeadersSize() {
        HarResponse response = new HarResponse();
        response.setHeadersSize(null);
        assertEquals(-1L, (long) response.getHeadersSize());
    }

    @Test
    void testBodySize() {
        HarResponse response = new HarResponse();
        response.setBodySize(null);
        assertEquals(-1L, (long) response.getBodySize());
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(HarResponse.class).verify();
    }
}