package de.sstoehr.harreader.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HarCache {

    private HarCacheInfo beforeRequest;
    private HarCacheInfo afterRequest;

    public HarCacheInfo getBeforeRequest() {
        return beforeRequest;
    }

    public void setBeforeRequest(HarCacheInfo beforeRequest) {
        this.beforeRequest = beforeRequest;
    }

    public HarCacheInfo getAfterRequest() {
        return afterRequest;
    }

    public void setAfterRequest(HarCacheInfo afterRequest) {
        this.afterRequest = afterRequest;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static final class HarCacheInfo {
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private Date expires;
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private Date lastAccess;
        private String eTag;
        private Integer hitCount;
        private String comment;

        public Date getExpires() {
            return expires;
        }

        public void setExpires(Date expires) {
            this.expires = expires;
        }

        public Date getLastAccess() {
            return lastAccess;
        }

        public void setLastAccess(Date lastAccess) {
            this.lastAccess = lastAccess;
        }

        public String geteTag() {
            return eTag;
        }

        public void seteTag(String eTag) {
            this.eTag = eTag;
        }

        public Integer getHitCount() {
            return hitCount;
        }

        public void setHitCount(Integer hitCount) {
            this.hitCount = hitCount;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
