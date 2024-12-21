package de.sstoehr.harreader.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum HttpMethod {
    GET, POST, PUT, HEAD, PROPFIND, OPTIONS, REPORT, DELETE, CONNECT, TRACE, CCM_POST, PATCH, UNKNOWN;

    @Nonnull
    public static HttpMethod fromString(@Nullable String method) {
        if (method == null) {
            return HttpMethod.UNKNOWN;
        }
        try {
            return HttpMethod.valueOf(method.toUpperCase());
        } catch (IllegalArgumentException e) {
            return HttpMethod.UNKNOWN;
        }
    }
}
