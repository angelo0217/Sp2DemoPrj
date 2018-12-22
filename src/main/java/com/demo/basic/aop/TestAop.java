package com.demo.basic.aop;

import com.demo.basic.annotation.MyAnnotation;
import com.demo.basic.vo.TestVo;
import com.demo.basic.vo.UserBookTestVo;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Aspect
@Component
public class TestAop {
	private static final Logger logger = LoggerFactory.getLogger(TestAop.class);

	@Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void doRequestBefore(JoinPoint joinPoint) throws Throwable {
		String name = joinPoint.getSignature().getName();
		logger.info("***aop before*** requestMapping name : " + name);

		Object[] objs = joinPoint.getArgs();
		for (Object obj : objs) {
			// System.out.println(JsonUtils.ObjToJson(obj));
			if (obj instanceof TestVo) {
				TestVo test = (TestVo) obj;
				if(test != null) {
					logger.info("***aop before*** get name :{}", test.getName());
				} else {
					logger.info("***aop before*** user is null  ");
				}
			} else if (obj instanceof UserBookTestVo){
				logger.info("***aop before*** user or book is : {}  ", new Gson().toJson(obj));
			}

		}
	}
	@AfterReturning(
			pointcut = "@annotation(org.springframework.web.bind.annotation.RequestMapping)",
			returning= "result")
	public void afterRequestMapping(JoinPoint joinPoint, Object result){
		logger.info("***aop after*** request mapping ");
		logger.info("***aop after*** request result :{} ", new Gson().toJson(result));
		Object[] objs = joinPoint.getArgs();
		for (Object obj : objs) {
			if (obj instanceof UserBookTestVo) {
				logger.info("after UserBookTestVo is : {}  ", new Gson().toJson(obj));
			}
		}
	}


	@Around("execution(@com.demo.basic.annotation.MyAnnotation * *(..)) && @annotation(myAnnotation)")
	public Object ｍyAnnotation(ProceedingJoinPoint joinPoint, MyAnnotation myAnnotation) throws Throwable {
		Object result = joinPoint.proceed();
		Object[] objs = joinPoint.getArgs();

		if(myAnnotation.dolog()) {
			for (Object obj : objs) {
				// System.out.println(JsonUtils.ObjToJson(obj));
				if (obj instanceof TestVo) {
					TestVo test = (TestVo) obj;
					if (test != null) {
						logger.info("***aop around*** around aop get name :{}", test.getName());
					} else {
						logger.info("***aop around*** user is null");
					}
				}
			}
			logger.info("***aop around*** Aop MyAnnotation :" + myAnnotation.value());
		}
		return result;
	}

}
