package com.demo.basic.service.repository;

import com.demo.basic.dao.UserInfoMapper;
import com.demo.basic.vo.domain.UserInfo;

import java.util.Optional;

/**
 * Created on 2018-12-21
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public interface UserInfoRepository extends UserInfoMapper {

    public Optional<UserInfo> selectByPrimaryKeyOps(Integer seq);
}