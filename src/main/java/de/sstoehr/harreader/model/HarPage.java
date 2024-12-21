package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Information about an exported page.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#pages">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public record HarPage(
        @Nullable @JsonFormat(shape = JsonFormat.Shape.STRING) ZonedDateTime startedDateTime,
        @Nullable String id,
        @Nullable String title,
        @Nonnull HarPageTiming pageTimings,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    public HarPage() {
        this(null, null, null, new HarPageTiming(), null, new HashMap<>());
    }

    public HarPage(@Nullable ZonedDateTime startedDateTime,
                   @Nullable String id,
                   @Nullable String title,
                   @Nullable HarPageTiming pageTimings,
                   @Nullable String comment,
                   @Nullable Map<String, Object> additional) {
        this.startedDateTime = startedDateTime;
        this.id = id;
        this.title = title;
        this.pageTimings = (pageTimings == null) ? new HarPageTiming() : pageTimings;
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
