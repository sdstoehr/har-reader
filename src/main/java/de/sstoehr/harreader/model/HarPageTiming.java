package de.sstoehr.harreader.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HarPageTiming {

    private Integer onContentLoad;
    private Integer onLoad;
    private String comment;

    public Integer getOnContentLoad() {
        return onContentLoad;
    }

    public void setOnContentLoad(Integer onContentLoad) {
        this.onContentLoad = onContentLoad;
    }

    public Integer getOnLoad() {
        return onLoad;
    }

    public void setOnLoad(Integer onLoad) {
        this.onLoad = onLoad;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
