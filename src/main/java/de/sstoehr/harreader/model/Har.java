package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Main HTTP Archive Class.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/">speicification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Har {

    @Valid
    @NotNull
    private HarLog log;

    public HarLog getLog() {
        return log;
    }

    /**
     * @throws java.lang.IllegalArgumentException if log is null.
     */
    public void setLog(HarLog log) {
        if (log == null) {
            throw new IllegalArgumentException("Log must not be null!");
        }
        this.log = log;
    }
}
