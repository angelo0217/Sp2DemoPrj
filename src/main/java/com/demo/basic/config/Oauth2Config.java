package com.demo.basic.config;

import com.demo.basic.service.security.impl.UserServiceImpl;
import com.demo.basic.service.test.impl.DemoClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    DemoClientDetailService demoClientDetailService;

    @Autowired
    @Qualifier("tokenRedis")
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private WebResponseExceptionTranslator demoResponseExceptionTranslator;
    /**
     *  配置token獲取合驗證時的策略
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security.allowFormAuthenticationForClients();
//        security
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(demoClientDetailService);
//        clients.inMemory()
//                .withClient("admin")
//                .authorizedGrantTypes("password","refresh_token", "authorization_code")
//                .authorities("ROLE_ADMIN")
//                .scopes("all")
//                .resourceIds(Const.DEMO_RESOURCE_ID)
//                .secret(EncryptUtil.getSecurityPwd("secret"))
//                .accessTokenValiditySeconds(360)
//                .and()
//
//                .withClient("client_r")
//                .authorizedGrantTypes("password","refresh_token")
//                .authorities("ROLE_CLIENT")
//                .scopes("read")
//                .resourceIds(Const.DEMO_RESOURCE_ID)
//                .secret(EncryptUtil.getSecurityPwd("secret"))
//                .accessTokenValiditySeconds(360)
//                .and()
//
//                .withClient("client_w")
//                .authorizedGrantTypes("password","refresh_token")
//                .authorities("ROLE_CLIENT")
//                .scopes("write")
//                .resourceIds(Const.DEMO_RESOURCE_ID)
//                .secret(EncryptUtil.getSecurityPwd("secret"))
//                .accessTokenValiditySeconds(360);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置tokenStore
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .authenticationManager(authenticationManager).userDetailsService(userService)
//                走Jwt token store
//                .accessTokenConverter(accessTokenConverter())
//                .tokenStore(jwtTokenStore());
//                走local store
//                .tokenStore(memoryTokenStore());
//                走Redis store
                .tokenStore(redisTokenStore());

        endpoints.exceptionTranslator(demoResponseExceptionTranslator);
    }

//     使用最基本的InMemoryTokenStore生成token
//    @Bean
    public TokenStore memoryTokenStore() {
        return new InMemoryTokenStore();
    }
//    @Bean
    public TokenStore jwtTokenStore() {
        TokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
        return tokenStore;
    }
    @Bean
    public JwtAccessTokenConverter  accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
            /***
             *  增強token的方法,自訂義一些token返回的信息
             */
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                String userName = authentication.getUserAuthentication().getName();

//                User user = (User) authentication.getUserAuthentication().getPrincipal();// 登陸後放進去的UserDetail實現類一直查看link{SecurityConfiguration}
                /** 自訂義一些token屬性 ***/
                final Map<String, Object> additionalInformation = new HashMap<>();
                additionalInformation.put("userName", userName);
//                additionalInformation.put("roles", user.getAuthorities());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
                return enhancedToken;
            }

        };
        //測試用，資源服務使用相同的字符達到一個對稱加密的效果，生產時候使用RSA非對稱加密方式
        accessTokenConverter.setSigningKey("123");
        return accessTokenConverter;
    }

    @Bean
    public TokenStore redisTokenStore() {
        RedisTokenStore redis = new RedisTokenStore(redisConnectionFactory);
        return redis;
    }
}
