package com.demo.basic.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class CacheVo {
    @JsonProperty("user_seq")
    private Integer userSeq;

    public Integer getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Integer userSeq) {
        this.userSeq = userSeq;
    }
}
