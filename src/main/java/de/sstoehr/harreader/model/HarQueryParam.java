package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;

/**
 * Information about query params.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#queryString">specification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarQueryParam {

    private String name;
    private String value;
    private String comment;

    /**
     * @return Name of param.
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
     * @return Value of param, may be null.
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
