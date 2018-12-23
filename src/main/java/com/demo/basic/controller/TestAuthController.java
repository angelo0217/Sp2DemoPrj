package com.demo.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 測試Oauth2 認證
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@RestController
public class TestAuthController {
    /**
     * 此方法在設定綁定 scope write
     * @return
     */
    @GetMapping("/doWrite/test")
    public String doWriteTest(){
        return "get write ok";
    }

    /**
     * 此方法在設定綁定 scope read
     * @return
     */
    @GetMapping("/doRead/test")
    public String doReadTest(){
        return "get read ok";
    }

    /**
     * 此方法在設定綁定 role admin( user認證的 )
     * @return
     */
    @GetMapping("/doAdmin/test")
    public String doAdminTest(){
        return "role admin ok";
    }
    /**
     * 此方法在設定綁定 role user( user認證的 非oauth2)
     * @return
     */
    @GetMapping("/doUser/test")
    public String doUserTest(){
        return "role user ok";
    }
    /**
     * 此方法在設定綁定 scope(read or write) role amin( user認證的 非oauth2)
     * @return
     */
    @GetMapping("/doDoubleChk/test")
    public String doDoublech(){
        return "role admin scope read ok";
    }
}
