package com.demo.basic.vo;

import com.demo.basic.Const;
import com.demo.basic.util.OauthClientUtils;
import com.demo.basic.vo.domain.Clients;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class BootClientDetailsVo implements ClientDetails {

    private Clients clients;

    public BootClientDetailsVo(Clients client) {
        this.clients = client;
    }

    public BootClientDetailsVo() {
    }

    @Override
    public String getClientId() {
        return clients.getClientid();
    }

    @Override
    public Set<String> getResourceIds() {
        return clients.getResourceids()!=null?
                OauthClientUtils.transformStringToSet(clients.getResourceids(),String.class):null;
    }

    @Override
    public boolean isSecretRequired() {
        return clients.getIssecretrequired();
    }

    @Override
    public String getClientSecret() {
        return Const.SECURITY_ENCRYPT + clients.getClientsecret();
    }

    @Override
    public boolean isScoped() {
        return clients.getIsscoped();
    }

    @Override
    public Set<String> getScope() {
        return clients.getScope()!=null?
                OauthClientUtils.transformStringToSet(clients.getScope(),String.class):null;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return clients.getAuthorizedgranttypes()!=null?
                OauthClientUtils.transformStringToSet(clients.getAuthorizedgranttypes(),String.class):null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return clients.getRegisteredredirecturi()!=null?
                OauthClientUtils.transformStringToSet(clients.getRegisteredredirecturi(),String.class):null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return (clients.getAuthorities()!=null&&clients.getAuthorities().trim().length()>0)?
                AuthorityUtils.commaSeparatedStringToAuthorityList(clients.getAuthorities()):null;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return clients.getAccesstokenvalidityseconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return clients.getRefreshtokenvalidityseconds();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return clients.getIsautoapprove();
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}