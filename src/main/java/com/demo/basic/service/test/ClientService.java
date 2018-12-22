package com.demo.basic.service.test;

import com.demo.basic.vo.domain.Clients;

/**
 * oauthException 取的Client
 *
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public interface ClientService {
    Clients findClientByClientId(String clientId);
}