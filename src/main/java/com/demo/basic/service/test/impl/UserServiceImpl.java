package com.demo.basic.service.test.impl;

import com.demo.basic.Const;
import com.demo.basic.dao.MemberMapper;
import com.demo.basic.service.test.UserService;
import com.demo.basic.vo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Vector;
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
    MemberMapper memberMapper;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Member> memberOps = getUser(userName);

        if(memberOps.isPresent()){
            List<GrantedAuthority> authorities = new Vector<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(memberOps.get().getUserrole()));

            return new User(memberOps.get().getUsername(), Const.SECURITY_ENCRYPT + memberOps.get().getUserpwd(), authorities);
        }

        return null;
    }

    private Optional<Member> getUser(String userName) throws UsernameNotFoundException  {
        Member member = memberMapper.selectByName(userName);

        return Optional.ofNullable(member);
    }
}
