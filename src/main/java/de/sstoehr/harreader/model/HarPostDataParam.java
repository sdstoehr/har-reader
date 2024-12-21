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
 * Information about POST params.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#params">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record HarPostDataParam(@Nullable String name,
        @Nullable String value,
        @Nullable String fileName,
        @Nullable String contentType,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    public HarPostDataParam() {
        this(null, null, null, null, null, new HashMap<>());
    }

    public HarPostDataParam(@Nullable String name,
                            @Nullable String value,
                            @Nullable String fileName,
                            @Nullable String contentType,
                            @Nullable String comment,
                            @Nullable Map<String, Object> additional) {
        this.name = name;
        this.value = value;
        this.fileName = fileName;
        this.contentType = contentType;
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
