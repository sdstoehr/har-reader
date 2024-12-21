package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Information about POST data.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#postData">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record HarPostData(
        @Nullable String mimeType,
        @Nonnull List<HarPostDataParam> params,
        @Nullable String text,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    public HarPostData() {
        this(null, Collections.emptyList(), null, null, new HashMap<>());
    }

    public HarPostData(@Nullable String mimeType,
                       @Nullable List<HarPostDataParam> params,
                       @Nullable String text,
                       @Nullable String comment,
                       @Nullable Map<String, Object> additional) {
        this.mimeType = mimeType;
        this.params = (params == null) ? Collections.emptyList() : params;
        this.text = text;
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
