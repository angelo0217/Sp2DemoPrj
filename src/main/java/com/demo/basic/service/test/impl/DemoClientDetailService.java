package com.demo.basic.service.test.impl;

import com.demo.basic.service.test.ClientService;
import com.demo.basic.vo.BootClientDetailsVo;
import com.demo.basic.vo.domain.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * 參考網址
 * https://my.oschina.net/yuit/blog/2413947
 * 客戶認證走DB
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Service
public class DemoClientDetailService implements ClientDetailsService {

    @Autowired
    private ClientService clientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        Clients client = this.clientService.findClientByClientId(clientId);

        if(client == null){
            throw new ClientRegistrationException("客户端不存在");
        }

        return new BootClientDetailsVo(client);
    }

}