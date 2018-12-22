package com.demo.basic.service.repository;

import com.demo.basic.dao.MemberMapper;
import com.demo.basic.vo.domain.Member;

import java.util.Optional;

public interface MemberRepository extends MemberMapper {
    Optional<Member> selectByNameOps(String userName);
}
