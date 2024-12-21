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
 * Information about events occurring during page load.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#pageTimings">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record HarPageTiming(
        @Nonnull Integer onContentLoad,
        @Nonnull Integer onLoad,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    protected static final Integer DEFAULT_TIME = -1;

    public HarPageTiming() {
        this(DEFAULT_TIME, DEFAULT_TIME, null, new HashMap<>());
    }

    public HarPageTiming(@Nullable Integer onContentLoad,
                         @Nullable Integer onLoad,
                         @Nullable String comment,
                         @Nullable Map<String, Object> additional) {
        this.onContentLoad = (onContentLoad == null) ? DEFAULT_TIME : onContentLoad;
        this.onLoad = (onLoad == null) ? DEFAULT_TIME : onLoad;
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
