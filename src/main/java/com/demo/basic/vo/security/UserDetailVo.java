package com.demo.basic.vo.security;

import com.demo.basic.Const;
import com.demo.basic.vo.domain.Member;
import com.demo.basic.vo.domain.UserInfo;
import com.google.gson.Gson;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * Created on 2018-12-22
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class UserDetailVo  implements UserDetails {

    private Member member;
    public UserDetailVo(Member member){
        System.out.println("==========================");
        System.out.println(new Gson().toJson(member));
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new Vector<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(member.getUserrole()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return Const.SECURITY_ENCRYPT + member.getUserpwd();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return member.getNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return member.getNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return member.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return member.getEnabled();
    }
}
