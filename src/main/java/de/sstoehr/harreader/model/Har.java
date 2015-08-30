package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Main HTTP Archive Class.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/">speicification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Har {

    private HarLog log;

    /**
     * @return HAR log.
     */
    public HarLog getLog() {
        if (log == null) {
            log = new HarLog();
        }
        return log;
    }

    public void setLog(HarLog log) {
        this.log = log;
    }
}
