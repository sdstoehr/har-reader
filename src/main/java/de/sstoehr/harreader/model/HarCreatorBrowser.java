package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Information about the application/browser used for creating HAR.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#creator">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record HarCreatorBrowser(
        @Nullable String name,
        @Nullable String version,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    public HarCreatorBrowser() {
        this(null, null, null, new HashMap<>());
    }

    public HarCreatorBrowser(@Nullable String name,
                             @Nullable String version,
                             @Nullable String comment,
                             @Nullable Map<String, Object> additional) {
        this.name = name;
        this.version = version;
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
