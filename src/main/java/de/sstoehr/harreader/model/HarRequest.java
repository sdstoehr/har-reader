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
 * Information about a performed request.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#request">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public record HarRequest(
        @Nullable String method,
        @Nullable String url,
        @Nullable String httpVersion,
        @Nonnull @Singular("cookie") List<HarCookie> cookies,
        @Nonnull @Singular("header") List<HarHeader> headers,
        @Nonnull @Singular("queryString") List<HarQueryParam> queryString,
        @Nonnull HarPostData postData,
        @Nonnull Long headersSize,
        @Nonnull Long bodySize,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    protected static final Long DEFAULT_SIZE = -1L;

    public HarRequest() {
        this(null, null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                new HarPostData(), DEFAULT_SIZE, DEFAULT_SIZE, null, new HashMap<>());
    }

    public HarRequest(@Nullable String method,
                      @Nullable String url,
                      @Nullable String httpVersion,
                      @Nullable List<HarCookie> cookies,
                      @Nullable List<HarHeader> headers,
                      @Nullable List<HarQueryParam> queryString,
                      @Nullable HarPostData postData,
                      @Nullable Long headersSize,
                      @Nullable Long bodySize,
                      @Nullable String comment,
                      @Nullable Map<String, Object> additional) {
        this.method = method;
        this.url = url;
        this.httpVersion = httpVersion;
        this.cookies = (cookies == null) ? Collections.emptyList() : cookies;
        this.headers = (headers == null) ? Collections.emptyList() : headers;
        this.queryString = (queryString == null) ? Collections.emptyList() : queryString;
        this.postData = (postData == null) ? new HarPostData() : postData;
        this.headersSize = (headersSize == null) ? DEFAULT_SIZE : headersSize;
        this.bodySize = (bodySize == null) ? DEFAULT_SIZE : bodySize;
        this.comment = comment;
        this.additional = (additional == null) ? new HashMap<>() : additional;
    }

    @JsonIgnore
    public HttpMethod httpMethod() {
        return HttpMethod.fromString(method);
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
