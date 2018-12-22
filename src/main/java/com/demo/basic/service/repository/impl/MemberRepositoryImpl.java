package com.demo.basic.service.repository.impl;

import com.demo.basic.dao.MemberMapper;
import com.demo.basic.service.repository.MemberRepository;
import com.demo.basic.vo.domain.Member;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    @Resource
    MemberMapper memberMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Member record) {
        return memberMapper.insert(record);
    }

    @Override
    public int insertSelective(Member record) {
        return memberMapper.insertSelective(record);
    }

    @Override
    public Member selectByPrimaryKey(Integer id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public Member selectByName(String userName) {
        return memberMapper.selectByName(userName);
    }

    public Optional<Member> selectByNameOps(String userName){
        return Optional.ofNullable(memberMapper.selectByName(userName));
    }
}
