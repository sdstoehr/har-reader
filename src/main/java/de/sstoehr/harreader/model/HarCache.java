package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Information about a request coming from browser cache.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#cache">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record HarCache(
        @Nullable HarCacheInfo beforeRequest,
        @Nullable HarCacheInfo afterRequest,
        @Nullable String comment,
        @Nonnull Map<String, Object> additional
) {

    public HarCache() {
        this(null, null, null, new HashMap<>());
    }

    public HarCache(@Nullable HarCacheInfo beforeRequest,
                    @Nullable HarCacheInfo afterRequest,
                    @Nullable String comment,
                    @Nullable Map<String, Object> additional) {
        this.beforeRequest = beforeRequest;
        this.afterRequest = afterRequest;
        this.comment = comment;
        this.additional = (additional == null) ? new HashMap<>() : additional;
    }

    @JsonAnyGetter
    public Map<String, Object> additional() {
        return additional;
    }

    @JsonAnySetter
    public void setAdditionalField(String key, Object value) {
        additional.put(key, value);
    }

    /**
     * Information about a request coming from browser cache.
     * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#cache">specification</a>
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record HarCacheInfo(@Nullable @JsonFormat(shape = JsonFormat.Shape.STRING) ZonedDateTime expires,
                                      @Nullable @JsonFormat(shape = JsonFormat.Shape.STRING) ZonedDateTime lastAccess,
                                      @Nullable String eTag,
                                      @Nullable Integer hitCount,
                                      @Nullable String comment,
                                      @Nonnull Map<String, Object> additional) {

        public HarCacheInfo() {
            this(null, null, null, null, null, new HashMap<>());
        }

        public HarCacheInfo(@Nullable ZonedDateTime expires,
                            @Nullable ZonedDateTime lastAccess,
                            @Nullable String eTag,
                            @Nullable Integer hitCount,
                            @Nullable String comment,
                            @Nullable Map<String, Object> additional) {
            this.expires = expires;
            this.lastAccess = lastAccess;
            this.eTag = eTag;
            this.hitCount = hitCount;
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
}
