package de.sstoehr.harreader.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HarLog {

    private String version;
    private HarCreatorBrowser creator;
    private HarCreatorBrowser browser;
    private List<HarPage> pages;
    private List<HarEntry> entries;
    private String comment;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public HarCreatorBrowser getCreator() {
        return creator;
    }

    public void setCreator(HarCreatorBrowser creator) {
        this.creator = creator;
    }

    public HarCreatorBrowser getBrowser() {
        return browser;
    }

    public void setBrowser(HarCreatorBrowser browser) {
        this.browser = browser;
    }

    public List<HarPage> getPages() {
        return pages;
    }

    public void setPages(List<HarPage> pages) {
        this.pages = pages;
    }

    public List<HarEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<HarEntry> entries) {
        this.entries = entries;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
