package com.demo.basic.controller;

import com.demo.basic.RtnCode;
import com.demo.basic.annotation.MyAnnotation;
import com.demo.basic.exception.DemoException;
import com.demo.basic.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 系統若拋出Exception 都會經過此
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@RestController
@ControllerAdvice
//@ControllerAdvice(basePackages ="com.demo.basic")
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    @MyAnnotation(value = "Exception", dolog = true)
    public Response exCenter(HttpServletRequest req, Exception ex){
        Response response = new Response();
        if(ex instanceof DemoException){
            DemoException demoException = (DemoException) ex;
            response.setCode(demoException.getErrCode());
            response.setMsg(demoException.getErrMsg());

            if(!StringUtils.isEmpty(demoException.getExMsg())){
                logger.error("exception :{}", demoException.getExMsg());
            }
        } else {
            response.setCode(RtnCode.SYSTEM_ERROR);
            response.setMsg("系統錯誤");
            logger.error("Path :{}", req.getContextPath());
            logger.error("exception :{}", ex.getMessage());
            ex.printStackTrace();
        }

        return response;
    }
}
