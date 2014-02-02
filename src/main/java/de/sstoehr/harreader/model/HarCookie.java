package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Information about a cookie used in request and/or response.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#cookies">specification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarCookie {

    private String name;
    private String value;
    private String path;
    private String domain;
    private Date expires;
    private Boolean httpOnly;
    private Boolean secure;
    private String comment;

    /**
     * @return Name of the cookie.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * @throws java.lang.IllegalArgumentException if name is null
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null!");
        }
        this.name = name;
    }

    /**
     * @return Value of the cookie.
     */
    @NotNull
    public String getValue() {
        return value;
    }

    /**
     * @throws java.lang.IllegalArgumentException if value is null
     */
    public void setValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null!");
        }
        this.value = value;
    }

    /**
     * @return The cookie's path, may be null.
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return The cookie's domain, may be null.
     */
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return The cookie's expiration time, may be null.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    /**
     * @return Whether the cookie is HTTP only, may be null.
     */
    public Boolean getHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(Boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    /**
     * @return Whether the cookie was transmitted via SSL, may be null.
     */
    public Boolean getSecure() {
        return secure;
    }

    public void setSecure(Boolean secure) {
        this.secure = secure;
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
