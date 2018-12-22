package com.demo.basic.controller;

import com.demo.basic.service.test.DbTestService;
import com.demo.basic.vo.Response;
import com.demo.basic.vo.UserBookTestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 測試db
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@RestController
public class DbTestController {

    @Autowired
    DbTestService dbTestService;

    /**
     * 寫入使用者資料
     * @param userBookTestVo
     * @return
     */
    @RequestMapping("/insertUser")
    public Response insertUser(@RequestBody UserBookTestVo userBookTestVo){
        return dbTestService.insertUser(userBookTestVo);
    }

    /**
     * 寫入書本資料
     * @param userBookTestVo
     * @return
     */
    @RequestMapping("/insertBook")
    public Response insertBook(@RequestBody UserBookTestVo userBookTestVo){
        return dbTestService.insertBook(userBookTestVo);
    }

    /**
     * 出錯時都RollBack測試
     * @param userBookTestVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/allRollBack")
    public Response allRollBack(@RequestBody UserBookTestVo userBookTestVo) throws Exception{
        return dbTestService.doAfterEx(userBookTestVo);
    }

    /**
     * 出錯時執行過的都不RollBack測試
     * @param userBookTestVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/doNotRollBack")
    public Response doNotRollBack(@RequestBody UserBookTestVo userBookTestVo) throws Exception{
        return dbTestService.doNotRollBack(userBookTestVo);
    }
}
