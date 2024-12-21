package de.sstoehr.harreader.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record HarTiming(
        @Nonnull Integer blocked,
        @Nonnull Integer dns,
        @Nonnull Integer connect,
        @Nullable Integer send,
        @JsonProperty("wait") @Nullable Integer waitTime,
        @Nullable Integer receive,
        @Nonnull Integer ssl,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    protected static final Integer DEFAULT_TIME = -1;

    public HarTiming() {
        this(DEFAULT_TIME, DEFAULT_TIME, DEFAULT_TIME,
                null, null, null, DEFAULT_TIME, null, new HashMap<>());
    }

    public HarTiming(@Nullable Integer blocked,
                     @Nullable Integer dns,
                     @Nullable Integer connect,
                     @Nullable Integer send,
                     @Nullable Integer waitTime,
                     @Nullable Integer receive,
                     @Nullable Integer ssl,
                     @Nullable String comment,
                     @Nullable Map<String, Object> additional) {
        this.blocked = (blocked == null) ? DEFAULT_TIME : blocked;
        this.dns = (dns == null) ? DEFAULT_TIME : dns;
        this.connect = (connect == null) ? DEFAULT_TIME : connect;
        this.send = send;
        this.waitTime = waitTime;
        this.receive = receive;
        this.ssl = (ssl == null) ? DEFAULT_TIME : ssl;
        this.comment = comment;
        this.additional = (additional == null) ? new HashMap<>() : additional;
    }

    /**
     * @return Map with additional keys, which are not officially supported by the HAR specification
     */
    @JsonAnyGetter
    public Map<String, Object> additional() {
        return additional;
    }

    @JsonAnySetter
    public void setAdditionalField(String key, Object value) {
        this.additional.put(key, value);
    }

}
