package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarTiming {

    protected static final Integer DEFAULT_TIME = -1;

    private Integer blocked;
    private Integer dns;
    private Integer connect;
    private Integer send;
    private Integer wait;
    private Integer receive;
    private Integer ssl;
    private String comment;

    /**
     * @return Time spent in a queue waiting for a network connection.
     * {@link #DEFAULT_TIME} if the timing does not apply to the current request.
     */
    public Integer getBlocked() {
        if (blocked == null) {
            return DEFAULT_TIME;
        }
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    /**
     * @return DNS resolution time. The time required to resolve a host name.
     * {@link #DEFAULT_TIME} if the timing does not apply to the current request.
     */
    public Integer getDns() {
        if (dns == null) {
            return DEFAULT_TIME;
        }
        return dns;
    }

    public void setDns(Integer dns) {
        this.dns = dns;
    }

    /**
     * @return Time required to create TCP connection.
     * {@link #DEFAULT_TIME} if the timing does not apply to the current request.
     */
    public Integer getConnect() {
        if (connect == null) {
            return DEFAULT_TIME;
        }
        return connect;
    }

    public void setConnect(Integer connect) {
        this.connect = connect;
    }

    /**
     * @return Time required to send HTTP request to the server, null if not present.
     */
    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {
        this.send = send;
    }

    /**
     * @return Waiting for a response from the server, null if not present.
     */
    public Integer getWait() {
        return wait;
    }

    public void setWait(Integer wait) {
        this.wait = wait;
    }

    /**
     * @return Time required to read entire response from the server (or cache), null if not present.
     */
    public Integer getReceive() {
        return receive;
    }

    public void setReceive(Integer receive) {
        this.receive = receive;
    }

    /**
     * @return Time required for SSL/TLS negotiation.
     * If this field is defined then the time is also included in the connect field
     * (to ensure backward compatibility with HAR 1.1).
     * {@link #DEFAULT_TIME} if the timing does not apply to the current request.
     */
    public Integer getSsl() {
        if (ssl == null) {
            return DEFAULT_TIME;
        }
        return ssl;
    }

    public void setSsl(Integer ssl) {
        this.ssl = ssl;
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
