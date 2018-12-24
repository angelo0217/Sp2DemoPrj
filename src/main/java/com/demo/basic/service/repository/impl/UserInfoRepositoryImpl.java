package com.demo.basic.service.repository.impl;

import com.demo.basic.dao.UserInfoMapper;
import com.demo.basic.service.repository.UserInfoRepository;
import com.demo.basic.vo.domain.UserInfo;
import com.demo.basic.vo.domain.UserInfoOps;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {
    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer seq) {
        return userInfoMapper.deleteByPrimaryKey(seq);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer seq) {
        return userInfoMapper.selectByPrimaryKey(seq);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<UserInfo> selectAll() {
        return userInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public Optional<UserInfo> getOptionalUser(int seq) {
        return Optional.empty();
    }

    @Override
    public Optional<UserInfo> selectByPrimaryKeyOps(Integer seq) {
        return Optional.ofNullable(selectByPrimaryKey(seq));
    }
}
