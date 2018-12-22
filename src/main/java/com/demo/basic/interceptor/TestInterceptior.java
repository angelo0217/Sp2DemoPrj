package com.demo.basic.interceptor;

import com.demo.basic.controller.CacheController;
import com.demo.basic.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Component
public class TestInterceptior extends HandlerInterceptorAdapter{
    private static final Logger logger = LoggerFactory.getLogger(TestInterceptior.class);

    /**
     * 運作前執行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("***Intercetor**** preHandle : {}", DateUtil.getNow());
        request.getSession().setAttribute("time", System.currentTimeMillis());
        return true;
    }

    /**
     * 運作後執行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        logger.info("***Intercetor**** postHandle : {}", DateUtil.getNow());
    }

    /**
     * 完全響應後 complie後執行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("***Intercetor**** afterCompletion : {}", DateUtil.getNow());
        System.out.println("use time : {}"+ ( System.currentTimeMillis() - (long) request.getSession().getAttribute("time")));
    }
}
