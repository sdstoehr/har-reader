package de.sstoehr.harreader.model;

public enum HttpMethod {
    GET, POST, PUT, HEAD, PROPFIND, OPTIONS, REPORT, DELETE, CONNECT, TRACE, CCM_POST, PATCH, UNKNOWN;

    public static HttpMethod fromString(String method) {
        try {
            return HttpMethod.valueOf(method.toUpperCase());
        } catch (IllegalArgumentException e) {
            return HttpMethod.UNKNOWN;
        }
    }
}
