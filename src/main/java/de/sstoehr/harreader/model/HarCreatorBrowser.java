package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;

/**
 * Information about the application/browser used for creating HAR.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#creator">specification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarCreatorBrowser {

    private String name;
    private String version;
    private String comment;

    /**
     * @return Name of the application/browser used for creating HAR.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * @throws java.lang.IllegalArgumentException if name is null
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null!");
        }
        this.name = name;
    }

    /**
     * @return Version of the application/browser used for creating HAR.
     */
    @NotNull
    public String getVersion() {
        return version;
    }

    /**
     * @throws java.lang.IllegalArgumentException if version is null
     */
    public void setVersion(String version) {
        if (version == null) {
            throw new IllegalArgumentException("Version must not be null!");
        }
        this.version = version;
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
