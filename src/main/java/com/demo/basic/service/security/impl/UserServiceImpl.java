package com.demo.basic.service.security.impl;

import com.demo.basic.service.repository.MemberRepository;
import com.demo.basic.service.security.UserService;
import com.demo.basic.vo.domain.Member;
import com.demo.basic.vo.security.UserDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        return new UserDetailVo(memberRepository.selectByNameOps(userName)
                .orElseThrow( ()-> new UsernameNotFoundException("not found user")));
    }

}
