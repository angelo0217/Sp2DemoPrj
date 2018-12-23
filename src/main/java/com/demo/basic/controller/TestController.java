package com.demo.basic.controller;

import com.demo.basic.annotation.MyAnnotation;
import com.demo.basic.dao.BookInfoMapper;
import com.demo.basic.dao.UserInfoMapper;
import com.demo.basic.vo.AsyncVo;
import com.demo.basic.vo.TestVo;
import com.demo.basic.vo.domain.BookInfo;
import com.demo.basic.vo.domain.UserInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 綁定annotation
     * @param testIn
     * @return
     */
    @RequestMapping("/hello")
    @MyAnnotation(value = "hello world", dolog = true)
    public TestVo hello(@RequestBody TestVo testIn){
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

    @RequestMapping("/hello3")
    public AsyncVo hello3(@RequestBody TestVo testIn ){
        Object[] objects = new Object[]{userInfoMapper, bookInfoMapper};
        CompletableFuture cf = CompletableFuture.completedFuture(objects).thenApplyAsync(objs -> {
            try {
                System.out.println(" 1 start");
                Thread.sleep(3000);
                UserInfo userInfo = ((UserInfoMapper) objs[0]).selectByPrimaryKey(1);
                System.out.println(new Gson().toJson(userInfo));
                return  userInfo;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                System.out.println(" 1 done");
            }
        }).thenCombine(CompletableFuture.completedFuture(objects).thenApplyAsync(objs -> {
            try {
                System.out.println(" 2 start");
                Thread.sleep(7000);
                BookInfo bookInfo = ((BookInfoMapper) objs[1]).selectByPrimaryKey(1);
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
}
