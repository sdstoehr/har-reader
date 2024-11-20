package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Root object of exported data.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#log">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarLog {

    protected static final String DEFAULT_VERSION = "1.1";

    private String version = DEFAULT_VERSION;
    private HarCreatorBrowser creator;
    private HarCreatorBrowser browser;
    private List<HarPage> pages = new ArrayList<>();
    private List<HarEntry> entries = new ArrayList<>();
    private String comment;
    private final Map<String, Object> additional = new HashMap<>();

    /**
     * @return Version number of the format.
     * Defaults to {@link #DEFAULT_VERSION}
     */
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        if (version == null || version.trim().isEmpty()) {
            version = DEFAULT_VERSION;
        }
        this.version = version;
    }

    /**
     * @return Information about the application used to generate HAR.
     */
    public HarCreatorBrowser getCreator() {
        if (creator == null) {
            creator = new HarCreatorBrowser();
        }
        return creator;
    }

    public void setCreator(HarCreatorBrowser creator) {
        this.creator = creator;
    }

    /**
     * @return Information about the browser used, may be null.
     */
    public HarCreatorBrowser getBrowser() {
        return browser;
    }

    public void setBrowser(HarCreatorBrowser browser) {
        this.browser = browser;
    }

    /**
     * @return List of all exported pages, may be empty.
     */
    public List<HarPage> getPages() {
        if (pages == null) {
            pages = new ArrayList<>();
        }
        return pages;
    }

    public void setPages(List<HarPage> pages) {
        this.pages = pages;
    }

    /**
     * @return List of all exported requests, may be empty.
     */
    public List<HarEntry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }

    public void setEntries(List<HarEntry> entries) {
        this.entries = entries;
    }

    /**
     * @return Comment provided by the user or application, null if not present.
     */
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return Map with additional keys, which are not officially supported by the HAR specification
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditional() {
        return additional;
    }

    @JsonAnySetter
    public void setAdditionalField(String key, Object value) {
        this.additional.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HarLog)) return false;
        HarLog harLog = (HarLog) o;
        return Objects.equals(version, harLog.version) &&
                Objects.equals(creator, harLog.creator) &&
                Objects.equals(browser, harLog.browser) &&
                Objects.equals(pages, harLog.pages) &&
                Objects.equals(entries, harLog.entries) &&
                Objects.equals(comment, harLog.comment) &&
                Objects.equals(additional, harLog.additional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, creator, browser, pages, entries, comment, additional);
    }
}
