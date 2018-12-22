package com.demo.basic.controller;

import com.demo.basic.service.test.DbTestService;
import com.demo.basic.vo.CacheVo;
import com.demo.basic.vo.Response;
import com.demo.basic.vo.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 測試Spring Cache方法
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@RestController
public class CacheController {

    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    DbTestService dbTestService;

    /**
     * 取得Ca
     * @param cacheVo
     * @return
     */
    @RequestMapping("/getCache")
    @Cacheable(cacheNames = "userInfo" , key = "#cacheVo.userSeq", condition = "#cacheVo.userSeq < 10")
    public UserInfo getCache(@RequestBody CacheVo cacheVo) {
        UserInfo userInfo = dbTestService.getUser(cacheVo.getUserSeq());
        return userInfo;
    }

    /**
     * 放入Cache
     * @param cacheVo
     * @return
     */
    @RequestMapping("/putCache")
    @CachePut(cacheNames = "userInfo", key = "#cacheVo.userSeq", condition = "#cacheVo.userSeq < 10")
    public UserInfo putCache(@RequestBody CacheVo cacheVo) {
        UserInfo userInfo = dbTestService.getUser(cacheVo.getUserSeq());
        return userInfo;
    }

    /**
     * 刪除緩存內的cache
     * @param cacheVo
     * @return
     */
    @RequestMapping("/deleteCache")
    @CacheEvict(cacheNames = "userInfo", key = "#cacheVo.userSeq", allEntries = true, beforeInvocation = true)
    public Response<String> deleteCache(@RequestBody CacheVo cacheVo) {
        Response<String> response = new Response();
        logger.info("delete cache seq : {}", cacheVo.getUserSeq());

        response.setCode(0);
        response.setData("刪除成功");

        return response;
    }
}
