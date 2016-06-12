package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Information about events occurring during page load.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#pageTimings">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarPageTiming {

    protected static final Integer DEFAULT_TIME = -1;

    private Integer onContentLoad = DEFAULT_TIME;
    private Integer onLoad = DEFAULT_TIME;
    private String comment;

    /**
     * @return Duration in ms until content is loaded.
     * {@link #DEFAULT_TIME} when no information available.
     */
    public Integer getOnContentLoad() {
        if (onContentLoad == null) {
            return DEFAULT_TIME;
        }
        return onContentLoad;
    }

    public void setOnContentLoad(Integer onContentLoad) {
        this.onContentLoad = onContentLoad;
    }

    /**
     * @return Duration in ms until onLoad event is fired.
     * {@link #DEFAULT_TIME} when no information available.
     */
    public Integer getOnLoad() {
        if (onLoad == null) {
            return DEFAULT_TIME;
        }
        return onLoad;
    }

    public void setOnLoad(Integer onLoad) {
        this.onLoad = onLoad;
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
