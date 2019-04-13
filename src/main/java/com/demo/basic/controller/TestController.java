package com.demo.basic.controller;

import com.demo.basic.annotation.MyAnnotation;
import com.demo.basic.dao.BookInfoMapper;
import com.demo.basic.dao.UserInfoMapper;
import com.demo.basic.vo.AsyncVo;
import com.demo.basic.vo.TestVo;
import com.demo.basic.vo.domain.BookInfo;
import com.demo.basic.vo.domain.UserInfo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.SmartDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.concurrent.CompletableFuture;

/**
 * 測試標準RestFul
 * aop + annotation
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    /**
     * 綁定annotation
     * @param testIn
     * @return
     */
    @RequestMapping("/hello")
    @MyAnnotation(value = "hello world", dolog = true)
    public TestVo hello(@RequestBody TestVo testIn){
        logger.info("controller print hello all");
        TestVo testVo = new TestVo();
        testVo.setName("hello");
        testVo.setAge(10);
        return testVo;
    }

    /**
     *
     * @param testIn
     * @return
     */
    @RequestMapping("/hello2")
    @MyAnnotation(value = "hello world 2")
    public TestVo hello2(@RequestBody TestVo testIn ){
        TestVo testVo = new TestVo();
        testVo.setName("hello");
        testVo.setAge(10);
        return testVo;
    }


    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    BookInfoMapper bookInfoMapper;

    @RequestMapping("/test_completable_future")
    public AsyncVo hello3(@RequestBody TestVo testIn ){
        int seq = 1;
        CompletableFuture cf = CompletableFuture.completedFuture(seq).thenApplyAsync(objs -> {
            try {
                System.out.println(" 1 run start");
                Thread.sleep(3000);
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(objs);
                System.out.println(new Gson().toJson(userInfo));
                return  userInfo;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                System.out.println(" 1 done");
            }
        }).thenCombine(CompletableFuture.completedFuture(seq).thenApplyAsync(objs -> {
            try {
                System.out.println(" 2 start");
                Thread.sleep(7000);
                BookInfo bookInfo = bookInfoMapper.selectByPrimaryKey(objs);
                System.out.println(new Gson().toJson(bookInfo));
                return bookInfo;
            } catch (Exception e) {
                return null;
            } finally {
                System.out.println(" 2 done");
            }
        }), (user, book) -> new AsyncVo(user, book ));
        return (AsyncVo) cf.join();
    }

    @Autowired
    BeanFactory beanFactory;
    @GetMapping("/closedb")
    public String close() throws Exception{
        DataSource dataSource = (DataSource) beanFactory.getBean("mysqlDataSource");
//        if (!(dataSource instanceof DataSource) || ((SmartDataSource) dataSource).shouldClose(dataSource.getConnection())) {
            dataSource.getConnection().close();
//        }
        return "success";
    }
}
