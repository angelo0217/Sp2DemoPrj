package com.demo.basic.dao;

import com.demo.basic.vo.domain.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Mapper
public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    Member selectByName(String userName);
}