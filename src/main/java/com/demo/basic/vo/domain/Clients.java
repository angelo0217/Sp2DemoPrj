package com.demo.basic.vo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class Clients implements Serializable {
    private String id;

    private String clientid;

    private String resourceids;

    private Boolean issecretrequired;

    private String clientsecret;

    private Boolean isscoped;

    private String scope;

    private String authorizedgranttypes;

    private String registeredredirecturi;

    private String authorities;

    private Boolean isautoapprove;

    private Integer accesstokenvalidityseconds;

    private Integer refreshtokenvalidityseconds;

    private Date createtime;

    private Date modifytime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid == null ? null : clientid.trim();
    }

    public String getResourceids() {
        return resourceids;
    }

    public void setResourceids(String resourceids) {
        this.resourceids = resourceids == null ? null : resourceids.trim();
    }

    public Boolean getIssecretrequired() {
        return issecretrequired;
    }

    public void setIssecretrequired(Boolean issecretrequired) {
        this.issecretrequired = issecretrequired;
    }

    public String getClientsecret() {
        return clientsecret;
    }

    public void setClientsecret(String clientsecret) {
        this.clientsecret = clientsecret == null ? null : clientsecret.trim();
    }

    public Boolean getIsscoped() {
        return isscoped;
    }

    public void setIsscoped(Boolean isscoped) {
        this.isscoped = isscoped;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public String getAuthorizedgranttypes() {
        return authorizedgranttypes;
    }

    public void setAuthorizedgranttypes(String authorizedgranttypes) {
        this.authorizedgranttypes = authorizedgranttypes == null ? null : authorizedgranttypes.trim();
    }

    public String getRegisteredredirecturi() {
        return registeredredirecturi;
    }

    public void setRegisteredredirecturi(String registeredredirecturi) {
        this.registeredredirecturi = registeredredirecturi == null ? null : registeredredirecturi.trim();
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities == null ? null : authorities.trim();
    }

    public Boolean getIsautoapprove() {
        return isautoapprove;
    }

    public void setIsautoapprove(Boolean isautoapprove) {
        this.isautoapprove = isautoapprove;
    }

    public Integer getAccesstokenvalidityseconds() {
        return accesstokenvalidityseconds;
    }

    public void setAccesstokenvalidityseconds(Integer accesstokenvalidityseconds) {
        this.accesstokenvalidityseconds = accesstokenvalidityseconds;
    }

    public Integer getRefreshtokenvalidityseconds() {
        return refreshtokenvalidityseconds;
    }

    public void setRefreshtokenvalidityseconds(Integer refreshtokenvalidityseconds) {
        this.refreshtokenvalidityseconds = refreshtokenvalidityseconds;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}