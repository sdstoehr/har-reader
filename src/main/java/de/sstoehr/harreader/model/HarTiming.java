package de.sstoehr.harreader.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public record HarTiming(
        @Nonnull Long blocked,
        @Nonnull Long dns,
        @Nonnull Long connect,
        @Nullable Long send,
        @JsonProperty("wait") @Nullable Long waitTime,
        @Nullable Long receive,
        @Nonnull Long ssl,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    protected static final Long DEFAULT_TIME = -1L;

    public HarTiming() {
        this(DEFAULT_TIME, DEFAULT_TIME, DEFAULT_TIME,
                null, null, null, DEFAULT_TIME, null, new HashMap<>());
    }

    public HarTiming(@Nullable Long blocked,
                     @Nullable Long dns,
                     @Nullable Long connect,
                     @Nullable Long send,
                     @Nullable Long waitTime,
                     @Nullable Long receive,
                     @Nullable Long ssl,
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
