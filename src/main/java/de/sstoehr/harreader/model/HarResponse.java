package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Singular;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Information about a performed response.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#response">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public record HarResponse(
        int status,
        @Nullable String statusText,
        @Nullable String httpVersion,
        @Nonnull @Singular("cookie") List<HarCookie> cookies,
        @Nonnull @Singular("header") List<HarHeader> headers,
        @Nonnull HarContent content,
        @Nullable String redirectURL,
        @Nonnull Long headersSize,
        @Nonnull Long bodySize,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    protected static final Long DEFAULT_SIZE = -1L;

    public HarResponse() {
        this(HttpStatus.UNKNOWN_HTTP_STATUS.getCode(), null, null,
                Collections.emptyList(), Collections.emptyList(), new HarContent(), null,
                DEFAULT_SIZE, DEFAULT_SIZE, null, new HashMap<>());
    }

    public HarResponse(int status,
                       @Nullable String statusText,
                       @Nullable String httpVersion,
                       @Nullable List<HarCookie> cookies,
                       @Nullable List<HarHeader> headers,
                       @Nullable HarContent content,
                       @Nullable String redirectURL,
                       @Nullable Long headersSize,
                       @Nullable Long bodySize,
                       @Nullable String comment,
                       @Nullable Map<String, Object> additional) {
        this.status = status;
        this.statusText = statusText;
        this.httpVersion = httpVersion;
        this.cookies = (cookies == null) ? Collections.emptyList() : cookies;
        this.headers = (headers == null) ? Collections.emptyList() : headers;
        this.content = (content == null) ? new HarContent() : content;
        this.redirectURL = redirectURL;
        this.headersSize = (headersSize == null) ? DEFAULT_SIZE : headersSize;
        this.bodySize = (bodySize == null) ? DEFAULT_SIZE : bodySize;
        this.comment = comment;
        this.additional = (additional == null) ? new HashMap<>() : additional;
    }

    @JsonIgnore
    public HttpStatus httpStatus() {
        return HttpStatus.byCode(status);
    }

    /**
     * @return Map with additional keys, which are not officially supported by the HAR specification
     */
    @JsonAnyGetter
    public Map<String, Object> additional() {
        return additional;
    }

    @JsonAnySetter
    public void setAdditionalField(String key, Object value) {
        this.additional.put(key, value);
    }

}
