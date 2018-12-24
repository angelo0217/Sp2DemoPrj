package com.demo.basic.config.oauthException;

import com.demo.basic.exception.DemoOauthException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/** *
 * Created on 2018-12-24
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class DemoAccessDeniedHandler extends AbstractOAuth2SecurityExceptionHandler implements AccessDeniedHandler {
    public DemoAccessDeniedHandler() {
    }
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        this.doHandle(httpServletRequest, httpServletResponse, new DemoOauthException(e.getMessage()));
    }
}
