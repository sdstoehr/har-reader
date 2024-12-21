package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Main HTTP Archive Class.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/">speicification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record Har(@Nonnull HarLog log) {

    public Har(@Nullable HarLog log) {
        this.log = (log == null) ? new HarLog() : log;
    }
}
