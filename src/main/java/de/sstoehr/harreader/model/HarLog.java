package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Root object of exported data.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#log">specification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarLog {

    protected static final String DEFAULT_VERSION = "1.1";

    private String version = DEFAULT_VERSION;
    private HarCreatorBrowser creator;
    private HarCreatorBrowser browser;
    private List<HarPage> pages = new ArrayList<>();
    private List<HarEntry> entries = new ArrayList<>();
    private String comment;

    /**
     * @return Version number of the format.
     * Defaults to {@link #DEFAULT_VERSION}
     */
    @NotNull
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        if (version == null || version.trim().equals("")) {
            version = DEFAULT_VERSION;
        }
        this.version = version;
    }

    /**
     * @return Information about the application used to generate HAR.
     */
    @Valid
    @NotNull
    public HarCreatorBrowser getCreator() {
        return creator;
    }

    /**
     * @throws java.lang.IllegalArgumentException if creator is null.
     */
    public void setCreator(HarCreatorBrowser creator) {
        if (creator == null) {
            throw new IllegalArgumentException("Creator must not be null!");
        }
        this.creator = creator;
    }

    /**
     * @return Information about the browser used, may be null.
     */
    @Valid
    public HarCreatorBrowser getBrowser() {
        return browser;
    }

    public void setBrowser(HarCreatorBrowser browser) {
        this.browser = browser;
    }

    /**
     * @return List of all exported pages, may be empty.
     */
    @Valid
    @NotNull
    public List<HarPage> getPages() {
        return pages;
    }

    public void setPages(List<HarPage> pages) {
        if (pages == null) {
            pages = new ArrayList<>();
        }
        this.pages = pages;
    }

    /**
     * @return List of all exported requests, may be empty.
     */
    @Valid
    @NotNull
    public List<HarEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<HarEntry> entries) {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        this.entries = entries;
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
