package com.demo.basic.dao;

import com.demo.basic.vo.domain.Clients;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Mapper
public interface ClientsMapper  {
    int deleteByPrimaryKey(String id);

    int insert(Clients record);

    int insertSelective(Clients record);

    Clients selectByPrimaryKey(String id);

    Clients findClientByClientId(String clientId);

    int updateByPrimaryKeySelective(Clients record);

    int updateByPrimaryKey(Clients record);
}