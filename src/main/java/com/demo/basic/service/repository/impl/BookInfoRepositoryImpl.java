package com.demo.basic.service.repository.impl;

import com.demo.basic.dao.BookInfoMapper;
import com.demo.basic.service.repository.BookInfoRepository;
import com.demo.basic.vo.domain.BookInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created on 2018-12-21
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Repository
public class BookInfoRepositoryImpl implements BookInfoRepository {
    @Resource
    BookInfoMapper bookInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer seq) {
        return bookInfoMapper.deleteByPrimaryKey(seq);
    }

    @Override
    public int insert(BookInfo record) {
        return bookInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(BookInfo record) {
        return bookInfoMapper.insertSelective(record);
    }

    @Override
    public BookInfo selectByPrimaryKey(Integer seq) {
        return bookInfoMapper.selectByPrimaryKey(seq);
    }

    @Override
    public int updateByPrimaryKeySelective(BookInfo record) {
        return bookInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BookInfo record) {
        return bookInfoMapper.updateByPrimaryKeySelective(record);
    }
}
