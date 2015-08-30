package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Information about a request coming from browser cache.
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#cache">specification</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarCache {

    private HarCacheInfo beforeRequest;
    private HarCacheInfo afterRequest;
    private String comment;

    /**
     * @return State of the cache entry before the request, null if not present.
     */
    public HarCacheInfo getBeforeRequest() {
        return beforeRequest;
    }

    public void setBeforeRequest(HarCacheInfo beforeRequest) {
        this.beforeRequest = beforeRequest;
    }

    /**
     * @return State of the cache entry after the request, null if not present.
     */
    public HarCacheInfo getAfterRequest() {
        return afterRequest;
    }

    public void setAfterRequest(HarCacheInfo afterRequest) {
        this.afterRequest = afterRequest;
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

    /**
     * Information about a request coming from browser cache.
     * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#cache">specification</a>
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class HarCacheInfo {

        private Date expires;
        private Date lastAccess;
        private String eTag;
        private Integer hitCount;
        private String comment;

        /**
         * @return Expiration time of entry, null if not present.
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        public Date getExpires() {
            return expires;
        }

        public void setExpires(Date expires) {
            this.expires = expires;
        }

        /**
         * @return Last time the entry was opened, null if not present.
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        public Date getLastAccess() {
            return lastAccess;
        }

        public void setLastAccess(Date lastAccess) {
            this.lastAccess = lastAccess;
        }

        /**
         * @return ETag, null if not present.
         */
        public String geteTag() {
            return eTag;
        }

        public void seteTag(String eTag) {
            this.eTag = eTag;
        }

        /**
         * @return Number of times the entry has been opened, null if not present.
         */
        public Integer getHitCount() {
            return hitCount;
        }

        public void setHitCount(Integer hitCount) {
            this.hitCount = hitCount;
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
}
