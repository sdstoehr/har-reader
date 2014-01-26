package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HarPage {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date startedDateTime;
    private String id;
    private String title;
    private HarPageTiming pageTimings;
    private String comment;

    public Date getStartedDateTime() {
        return startedDateTime;
    }

    public void setStartedDateTime(Date startedDateTime) {
        this.startedDateTime = startedDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HarPageTiming getPageTimings() {
        return pageTimings;
    }

    public void setPageTimings(HarPageTiming pageTimings) {
        this.pageTimings = pageTimings;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
