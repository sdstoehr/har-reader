package de.sstoehr.harreader.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Information about the response's content.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#content">specification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarContent {

    private Long size;
    private Long compression;
    private String mimeType;
    private String text;
    private String encoding;
    private String comment;

    /**
     * @return Length of returned content in bytes.
     */
    @NotNull
    public Long getSize() {
        return size;
    }

    /**
     * @throws java.lang.IllegalArgumentException if size is null.
     */
    public void setSize(Long size) {
        if (size == null) {
            throw new IllegalArgumentException("Size must not be null!");
        }
        this.size = size;
    }

    /**
     * @return Number of byted saved by compression, may be null.
     */
    public Long getCompression() {
        return compression;
    }

    public void setCompression(Long compression) {
        this.compression = compression;
    }

    /**
     * @return MIME-Type of response. May include the charset.
     */
    @NotNull
    public String getMimeType() {
        return mimeType;
    }
    
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @return Response body loaded from server or cache, may be null.
     * Binary content may be encoded using encoding specified by {@link #getEncoding()}.
     */
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return Encoding used for encoding response body, may be null.
     * @see #getText()
     */
    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
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
