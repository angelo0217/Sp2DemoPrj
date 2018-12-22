package com.demo.basic.dao;

import com.demo.basic.vo.domain.BookInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Mapper
public interface BookInfoMapper {
    int deleteByPrimaryKey(Integer seq);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    BookInfo selectByPrimaryKey(Integer seq);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);
}