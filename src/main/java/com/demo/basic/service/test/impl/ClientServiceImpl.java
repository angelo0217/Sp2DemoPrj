package com.demo.basic.service.test.impl;

import com.demo.basic.dao.ClientsMapper;
import com.demo.basic.service.test.ClientService;
import com.demo.basic.vo.domain.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientsMapper clientsMapper;

    @Override
    public Clients findClientByClientId(String clientId) {
        return this.clientsMapper.findClientByClientId(clientId);
    }
}