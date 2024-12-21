package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Information about a single HTTP request.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#entries">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public record HarEntry(
        @Nullable String pageref,
        @Nullable @JsonFormat(shape = JsonFormat.Shape.STRING) ZonedDateTime startedDateTime,
        @Nullable Integer time,
        @Nonnull HarRequest request,
        @Nonnull HarResponse response,
        @Nonnull HarCache cache,
        @Nonnull HarTiming timings,
        @Nullable String serverIPAddress,
        @Nullable String connection,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional) {

    public HarEntry() {
        this(null, null, null, new HarRequest(), new HarResponse(),
                new HarCache(), new HarTiming(), null, null, null, new HashMap<>());
    }

    public HarEntry(@Nullable String pageref,
                    @Nullable ZonedDateTime startedDateTime,
                    @Nullable Integer time,
                    @Nullable HarRequest request,
                    @Nullable HarResponse response,
                    @Nullable HarCache cache,
                    @Nullable HarTiming timings,
                    @Nullable String serverIPAddress,
                    @Nullable String connection,
                    @Nullable String comment,
                    @Nullable Map<String, Object> additional) {
        this.pageref = pageref;
        this.startedDateTime = startedDateTime;
        this.time = time;
        this.request = (request == null) ? new HarRequest() : request;
        this.response = (response == null) ? new HarResponse() : response;
        this.cache = (cache == null) ? new HarCache() : cache;
        this.timings = (timings == null) ? new HarTiming() : timings;
        this.serverIPAddress = serverIPAddress;
        this.connection = connection;
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
