package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Information about a performed request.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#request">specification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarRequest {

    private HttpMethod method;
    private String url;
    private String httpVersion;
    private List<HarCookie> cookies;
    private List<HarHeader> headers;
    private List<HarQueryParam> queryString;
    private HarPostData postData;
    private Long headersSize;
    private Long bodySize;
    private String comment;

    @NotNull
    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    @NotNull
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NotNull
    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    @NotNull
    public List<HarCookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<HarCookie> cookies) {
        this.cookies = cookies;
    }

    @NotNull
    public List<HarHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HarHeader> headers) {
        this.headers = headers;
    }

    @NotNull
    @Valid
    public List<HarQueryParam> getQueryString() {
        return queryString;
    }

    public void setQueryString(List<HarQueryParam> queryString) {
        this.queryString = queryString;
    }

    @Valid
    public HarPostData getPostData() {
        return postData;
    }

    public void setPostData(HarPostData postData) {
        this.postData = postData;
    }

    @NotNull
    public Long getHeadersSize() {
        return headersSize;
    }

    public void setHeadersSize(Long headersSize) {
        this.headersSize = headersSize;
    }

    @NotNull
    public Long getBodySize() {
        return bodySize;
    }

    public void setBodySize(Long bodySize) {
        this.bodySize = bodySize;
    }

    /**
     * @return Comment provided by the user or application, may be null.
     */
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
