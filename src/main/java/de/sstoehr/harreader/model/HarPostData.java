package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Information about POST data.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#postData">specification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarPostData {

    private String mimeType;
    private List<HarPostDataParam> params = new ArrayList<>();
    private String text;
    private String comment;

    /**
     * @return MIME type of posted data.
     */
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @return List of posted params.
     */
    @NotNull
    @Valid
    public List<HarPostDataParam> getParams() {
        return params;
    }

    /**
     * @throws java.lang.IllegalArgumentException if params is null.
     */
    public void setParams(List<HarPostDataParam> params) {
        if (params == null) {
            throw new IllegalArgumentException("Params must not be null!");
        }
        this.params = params;
    }

    /**
     * @return Plain text posted data.
     */
    @NotNull
    public String getText() {
        return text;
    }

    /**
     * @throws java.lang.IllegalArgumentException if text is null.
     */
    public void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text must not be null!");
        }
        this.text = text;
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
