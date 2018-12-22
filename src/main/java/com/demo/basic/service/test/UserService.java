package com.demo.basic.service.test;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public interface UserService {
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException;
}
