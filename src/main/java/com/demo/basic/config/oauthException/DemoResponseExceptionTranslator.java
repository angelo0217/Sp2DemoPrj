package com.demo.basic.config.oauthException;

import com.demo.basic.exception.DemoOauthException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;
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
@Component("demoResponseExceptionTranslator")
public class DemoResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;

        return ResponseEntity
                .status(oAuth2Exception.getHttpErrorCode())
                .body(new DemoOauthException(oAuth2Exception.getMessage()));
    }
}