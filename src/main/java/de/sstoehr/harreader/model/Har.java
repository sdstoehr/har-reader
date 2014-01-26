package de.sstoehr.harreader.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Main HTTP Archive Class.
 * @link{http://www.softwareishard.com/blog/har-12-spec/#log}
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Har {

    private HarLog log;

    public HarLog getLog() {
        return log;
    }

    public void setLog(HarLog log) {
        this.log = log;
    }
}
