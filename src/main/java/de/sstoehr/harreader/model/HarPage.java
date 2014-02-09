package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Information about an exported page.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#pages">specification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarPage {

    private Date startedDateTime;
    private String id;
    private String title;
    private HarPageTiming pageTimings;
    private String comment;

    /**
     * @return Start time of page load.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull
    public Date getStartedDateTime() {
        return startedDateTime;
    }

    /**
     * @throws java.lang.IllegalArgumentException if startedDateTime is null.
     */
    public void setStartedDateTime(Date startedDateTime) {
        if (startedDateTime == null) {
            throw new IllegalArgumentException("StartedDateTime must not be null!");
        }
        this.startedDateTime = startedDateTime;
    }

    /**
     * @return Unique identifier.
     */
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * @throws java.lang.IllegalArgumentException if id is null.
     */
    public void setId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null!");
        }
        this.id = id;
    }

    /**
     * @return Page title.
     */
    @NotNull
    public String getTitle() {
        return title;
    }

    /**
     * @throws java.lang.IllegalArgumentException if title is null.
     */
    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title must not be null!");
        }
        this.title = title;
    }

    /**
     * @return Detailed information about page loading timings.
     */
    @NotNull
    @Valid
    public HarPageTiming getPageTimings() {
        return pageTimings;
    }

    /**
     * @throws java.lang.IllegalArgumentException if pageTimings is null.
     */
    public void setPageTimings(HarPageTiming pageTimings) {
        if (pageTimings == null) {
            throw new IllegalArgumentException("PageTimings must not be null!");
        }
        this.pageTimings = pageTimings;
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
