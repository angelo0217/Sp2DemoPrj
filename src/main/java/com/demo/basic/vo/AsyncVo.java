package com.demo.basic.vo;

import com.demo.basic.vo.domain.BookInfo;
import com.demo.basic.vo.domain.UserInfo;
/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class AsyncVo {
    public AsyncVo(UserInfo userInfo, BookInfo bookInfo){
        this.userInfo = userInfo;
        this.bookInfo = bookInfo;
    }
    private UserInfo userInfo;
    private BookInfo bookInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }
}
