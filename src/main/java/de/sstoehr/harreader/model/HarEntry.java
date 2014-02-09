package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Information about a single HTTP request.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#entries">specification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarEntry {

    private String pageref;
    private Date startedDateTime;
    private Integer time;
    private HarRequest request;
    private HarResponse response;
    private HarCache cache;
    private HarTiming timings;
    private String serverIPAddress;
    private String connection;
    private String comment;

    /**
     * @return Reference to parent page, to which the request belongs to, may be null.
     */
    public String getPageref() {
        return pageref;
    }

    public void setPageref(String pageref) {
        this.pageref = pageref;
    }

    /**
     * @return Start time of request.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull
    public Date getStartedDateTime() {
        return startedDateTime;
    }

    /**
     * @throws java.lang.IllegalArgumentException if startedDateTime is null.
     */
    public void setStartedDateTime(Date startedDateTime) {
        if (startedDateTime == null) {
            throw new IllegalArgumentException("StartedDateTime must not be null!");
        }
        this.startedDateTime = startedDateTime;
    }

    /**
     * @return Total request time (in ms).
     */
    @NotNull
    public Integer getTime() {
        return time;
    }

    /**
     * @throws java.lang.IllegalArgumentException if time is null.
     */
    public void setTime(Integer time) {
        if (time == null) {
            throw new IllegalArgumentException("Time must not be null!");
        }
        this.time = time;
    }

    /**
     * @return Detailed request information.
     */
    @NotNull
    @Valid
    public HarRequest getRequest() {
        return request;
    }

    /**
     * @throws java.lang.IllegalArgumentException if request is null.
     */
    public void setRequest(HarRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request must not be null!");
        }
        this.request = request;
    }

    /**
     * @return Detailed response information.
     */
    @NotNull
    @Valid
    public HarResponse getResponse() {
        return response;
    }

    /**
     * @throws java.lang.IllegalArgumentException if response is null.
     */
    public void setResponse(HarResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("Response must not be null!");
        }
        this.response = response;
    }

    /**
     * @return Information about cache usage.
     */
    @NotNull
    @Valid
    public HarCache getCache() {
        return cache;
    }

    /**
     * @throws java.lang.IllegalArgumentException if cache is null.
     */
    public void setCache(HarCache cache) {
        if (cache == null) {
            throw new IllegalArgumentException("Cache must not be null!");
        }
        this.cache = cache;
    }

    /**
     * @return Detailed information about request/response timings.
     */
    @NotNull
    @Valid
    public HarTiming getTimings() {
        return timings;
    }

    /**
     * @throws java.lang.IllegalArgumentException if timings is null.
     */
    public void setTimings(HarTiming timings) {
        if (timings == null) {
            throw new IllegalArgumentException("Timings must not be null!");
        }
        this.timings = timings;
    }

    /**
     * @return Server IP address (result of DNS resolution), may be null.
     */
    public String getServerIPAddress() {
        return serverIPAddress;
    }

    public void setServerIPAddress(String serverIPAddress) {
        this.serverIPAddress = serverIPAddress;
    }

    /**
     * @return Unique ID of TCP/IP connection, may be null.
     */
    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
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
