package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Information about an exported page.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#pages">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarPage {

    private Date startedDateTime;
    private String id;
    private String title;
    private HarPageTiming pageTimings;
    private String comment;

    /**
     * @return Start time of page load, null if not present.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Date getStartedDateTime() {
        return startedDateTime;
    }

    public void setStartedDateTime(Date startedDateTime) {
        this.startedDateTime = startedDateTime;
    }

    /**
     * @return Unique identifier, null if not present.
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Page title, null if not present.
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Detailed information about page loading timings.
     */
    public HarPageTiming getPageTimings() {
        if (pageTimings == null) {
            pageTimings = new HarPageTiming();
        }
        return pageTimings;
    }

    public void setPageTimings(HarPageTiming pageTimings) {
        this.pageTimings = pageTimings;
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
}
