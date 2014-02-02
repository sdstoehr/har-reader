package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Information about a request coming from browser cache.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#cache">specification</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarCache {

    private HarCacheInfo beforeRequest;
    private HarCacheInfo afterRequest;
    private String comment;

    /**
     * @return State of the cache entry before the request, may be null.
     */
    @Valid
    public HarCacheInfo getBeforeRequest() {
        return beforeRequest;
    }

    public void setBeforeRequest(HarCacheInfo beforeRequest) {
        this.beforeRequest = beforeRequest;
    }

    /**
     * @return State of the cache entry after the request, may be null.
     */
    @Valid
    public HarCacheInfo getAfterRequest() {
        return afterRequest;
    }

    public void setAfterRequest(HarCacheInfo afterRequest) {
        this.afterRequest = afterRequest;
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

    /**
     * Information about a request coming from browser cache.
     * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#cache">specification</a>
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class HarCacheInfo {

        private Date expires;
        private Date lastAccess;
        private String eTag;
        private Integer hitCount;
        private String comment;

        /**
         * @return Expiration time of entry, may be null.
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        public Date getExpires() {
            return expires;
        }

        public void setExpires(Date expires) {
            this.expires = expires;
        }

        /**
         * @return Last time the entry was opened.
         */
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        public Date getLastAccess() {
            return lastAccess;
        }

        /**
         * @throws java.lang.IllegalArgumentException if lastAccess is null
         */
        public void setLastAccess(Date lastAccess) {
            if (lastAccess == null) {
                throw new IllegalArgumentException("LastAccess must not be null!");
            }
            this.lastAccess = lastAccess;
        }

        /**
         * @return ETag.
         */
        @NotNull
        public String geteTag() {
            return eTag;
        }

        public void seteTag(String eTag) {
            this.eTag = eTag;
        }

        /**
         * @return Number of times the entry has been opened.
         */
        @NotNull
        public Integer getHitCount() {
            return hitCount;
        }

        /**
         * @throws java.lang.IllegalArgumentException if hitCount is null
         */
        public void setHitCount(Integer hitCount) {
            if (hitCount == null) {
                throw new IllegalArgumentException("HitCount must not be null!");
            }
            this.hitCount = hitCount;
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
}
