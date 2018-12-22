package com.demo.basic.config.oauthException;

import com.demo.basic.RtnCode;
import com.demo.basic.vo.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class DemoAuthExceptionPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws ServletException {

        Response res = new Response();
        res.setCode(RtnCode.AUTH_ERROR);
        res.setMsg(authException.getMessage());
        res.setPath(request.getServletPath());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), res);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}