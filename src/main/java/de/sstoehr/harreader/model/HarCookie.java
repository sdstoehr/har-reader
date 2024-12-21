package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Information about a cookie used in request and/or response.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#cookies">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record HarCookie(
        @Nullable String name,
        @Nullable String value,
        @Nullable String path,
        @Nullable String domain,
        @Nullable @JsonFormat(shape = JsonFormat.Shape.STRING) ZonedDateTime expires,
        @Nullable Boolean httpOnly,
        @Nullable Boolean secure,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    public HarCookie() {
        this(null, null, null, null, null, null, null, null, new HashMap<>());
    }

    public HarCookie(@Nullable String name,
                     @Nullable String value,
                     @Nullable String path,
                     @Nullable String domain,
                     @Nullable ZonedDateTime expires,
                     @Nullable Boolean httpOnly,
                     @Nullable Boolean secure,
                     @Nullable String comment,
                     @Nullable Map<String, Object> additional) {
        this.name = name;
        this.value = value;
        this.path = path;
        this.domain = domain;
        this.expires = expires;
        this.httpOnly = httpOnly;
        this.secure = secure;
        this.comment = comment;
        this.additional = (additional == null) ? new HashMap<>() : additional;
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
