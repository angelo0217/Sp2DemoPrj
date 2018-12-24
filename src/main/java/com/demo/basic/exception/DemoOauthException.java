package com.demo.basic.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自訂義錯誤
 * 參考網址https://blog.csdn.net/dandandeshangni/article/details/80472147
 *
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@JsonSerialize(using = DemoOauthExceptionSerializer.class)
public class DemoOauthException extends OAuth2Exception {
    public DemoOauthException(String msg) {
        super(msg);
    }
}