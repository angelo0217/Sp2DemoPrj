package com.demo.basic.service.test.impl;

import com.demo.basic.RtnCode;
import com.demo.basic.exception.DemoException;
import com.demo.basic.service.repository.BookInfoRepository;
import com.demo.basic.service.repository.UserInfoRepository;
import com.demo.basic.service.test.DbTestService;
import com.demo.basic.vo.Response;
import com.demo.basic.vo.UserBookTestVo;
import com.demo.basic.vo.domain.BookInfo;
import com.demo.basic.vo.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Service
public class DbTestServiceImpl implements DbTestService {

    private static final Logger logger = LoggerFactory.getLogger(DbTestServiceImpl.class);

    @Autowired
    BookInfoRepository bookInfoRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public Response<Integer> insertUser(UserBookTestVo userBookTestVo) {
        Response response = new Response();
        response.setCode(RtnCode.SUCCESS);

        UserInfo userInfo = new UserInfo();
        userInfo.setName(userBookTestVo.getName());
        userInfo.setAge(userBookTestVo.getAge());

        response.setData(userInfoRepository.insert(userInfo));
        return response;
    }

    @Override
    public Response<Integer> insertBook(UserBookTestVo userBookTestVo) {
        Response response = new Response();
        response.setCode(RtnCode.SUCCESS);

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName(userBookTestVo.getBookName());
        bookInfo.setBookPrice(userBookTestVo.getBookPrice());

        response.setData(bookInfoRepository.insert(bookInfo));
        return response;
    }

    @Override
    @Transactional(rollbackFor  = Exception.class)
    public Response<String> doAfterEx(UserBookTestVo userBookTestVo) throws Exception{
        Response response = new Response();
        response.setCode(RtnCode.SUCCESS);

        UserInfo userInfo = new UserInfo();
        userInfo.setName(userBookTestVo.getName());
        userInfo.setAge(userBookTestVo.getAge());
        userInfoRepository.insert(userInfo);

        logger.info("insert user : {}", userInfo.getName());

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName(userBookTestVo.getBookName());
        bookInfo.setBookPrice(userBookTestVo.getBookPrice());
        bookInfoRepository.insert(bookInfo);

        logger.info("insert book : {}", bookInfo.getBookName());
        if(userBookTestVo.isThrowEx()){

            logger.info("ops error do rollback");
            throw new DemoException(RtnCode.ERROR, "throw ex 測試roll back", null);
        }

        response.setData("do insert ok");
        return response;
    }

    @Override
    @Transactional(noRollbackFor  = Exception.class)
    public Response<String> doNotRollBack(UserBookTestVo userBookTestVo) throws Exception {
        Response response = new Response();
        response.setCode(RtnCode.SUCCESS);

        UserInfo userInfo = new UserInfo();
        userInfo.setName(userBookTestVo.getName());
        userInfo.setAge(userBookTestVo.getAge());
        userInfoRepository.insert(userInfo);
        logger.info("insert user : {}", userInfo.getName());

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName(userBookTestVo.getBookName());
        bookInfo.setBookPrice(userBookTestVo.getBookPrice());
        bookInfoRepository.insert(bookInfo);
        logger.info("insert book : {}", bookInfo.getBookName());

        if(userBookTestVo.isThrowEx()){
            logger.info("ops error always not rollback");
            throw new DemoException(RtnCode.ERROR, "throw ex 測試 do not roll back", null);
        }
        response.setData("do insert ok");
        return response;
    }

    @Override
    public UserInfo getUser(int seq) {

        return userInfoRepository.selectByPrimaryKey(seq);
    }
}
