package de.sstoehr.harreader.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HttpStatusTest {

    @Test
    void testByCode() {
        for (HttpStatus status : HttpStatus.values()) {
            assertEquals(status, HttpStatus.byCode(status.getCode()));
        }
    }

    @Test
    void test302() {
        assertEquals(HttpStatus.FOUND, HttpStatus.byCode(302));
    }

    @Test
    void testInvalidCode() {
        assertEquals(HttpStatus.UNKNOWN_HTTP_STATUS, HttpStatus.byCode(0));
        assertEquals(HttpStatus.UNKNOWN_HTTP_STATUS, HttpStatus.byCode(1000));
        assertEquals(HttpStatus.UNKNOWN_HTTP_STATUS, HttpStatus.byCode(-999));
    }

}
