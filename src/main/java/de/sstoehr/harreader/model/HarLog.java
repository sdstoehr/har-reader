package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Singular;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Root object of exported data.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#log">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public record HarLog(
        @Nonnull String version,
        @Nonnull HarCreatorBrowser creator,
        @Nullable HarCreatorBrowser browser,
        @Nonnull @Singular("page") List<HarPage> pages,
        @Nonnull @Singular("entry") List<HarEntry> entries,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    protected static final String DEFAULT_VERSION = "1.1";

    public HarLog() {
        this(DEFAULT_VERSION, new HarCreatorBrowser(), null, Collections.emptyList(), Collections.emptyList(), null, new HashMap<>());
    }

    public HarLog(@Nullable String version,
                  @Nullable HarCreatorBrowser creator,
                  @Nullable HarCreatorBrowser browser,
                  @Nullable List<HarPage> pages,
                  @Nullable List<HarEntry> entries,
                  @Nullable String comment,
                  @Nullable Map<String, Object> additional) {
        this.version = (version == null || version.isBlank()) ? DEFAULT_VERSION : version;
        this.creator = (creator == null) ? new HarCreatorBrowser() : creator;
        this.browser = browser;
        this.pages = (pages == null) ? Collections.emptyList() : pages;
        this.entries = (entries == null) ? Collections.emptyList() : entries;
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
