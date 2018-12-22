package com.demo.basic.service.test;

import com.demo.basic.vo.Response;
import com.demo.basic.vo.domain.UserInfo;
import com.demo.basic.vo.UserBookTestVo;
/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public interface DbTestService {
    /**
     * 寫入使用者
     * @param userBookTestVo
     * @return
     */
    Response<Integer> insertUser(UserBookTestVo userBookTestVo);
    /**
     * 寫入書本
     * @param userBookTestVo
     * @return
     */
    Response<Integer> insertBook(UserBookTestVo userBookTestVo);
    /**
     *
     * @param userBookTestVo
     * @return
     */
    Response<String> doAfterEx(UserBookTestVo userBookTestVo) throws Exception;
    /**
     * 寫入2者，判斷是中間否拋Exception
     * @param userBookTestVo
     * @return
     */
    Response<String> doNotRollBack(UserBookTestVo userBookTestVo) throws Exception;

    /**
     * 取得使用者
     * @param seq
     * @return
     */
    UserInfo getUser(int seq);
}

