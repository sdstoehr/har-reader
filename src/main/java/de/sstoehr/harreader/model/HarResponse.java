package de.sstoehr.harreader.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HarResponse {

    private HttpStatus status;
    private String httpVersion;
    private List<HarCookie> cookies;
    private List<HarHeader> headers;
    private HarContent content;
    private String redirectURL;
    private Long headersSize;
    private Long bodySize;
    private String comment;

    public int getStatus() {
        return status.getCode();
    }

    public void setStatus(int status) {
        this.status = HttpStatus.getByCode(status);
    }

    public String getStatusText() {
        String[] text = status.getText();
        if (text == null || text.length == 0) {
            return null;
        }
        return text[0];
    }

    public void setStatusText(String statusText) {
        this.status = HttpStatus.getByText(statusText);
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public List<HarCookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<HarCookie> cookies) {
        this.cookies = cookies;
    }

    public List<HarHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HarHeader> headers) {
        this.headers = headers;
    }

    public HarContent getContent() {
        return content;
    }

    public void setContent(HarContent content) {
        this.content = content;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    public Long getHeadersSize() {
        return headersSize;
    }

    public void setHeadersSize(Long headersSize) {
        this.headersSize = headersSize;
    }

    public Long getBodySize() {
        return bodySize;
    }

    public void setBodySize(Long bodySize) {
        this.bodySize = bodySize;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
