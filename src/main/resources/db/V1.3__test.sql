-- 客户端表
create table clients (
  id varchar(32) primary key,
  clientId varchar(100) not null,
  resourceIds varchar(255),
  isSecretRequired bit,
  clientSecret varchar(100),
  isScoped bit,
  scope varchar(255),
  authorizedGrantTypes varchar(255) not null,
  registeredRedirectUri varchar(255),
  authorities varchar(255),
  isAutoApprove bit,
  accessTokenValiditySeconds int ,
  refreshTokenValiditySeconds int,
  createTime timestamp default current_timestamp comment '创建时间',
  modifyTime timestamp default current_timestamp on update current_timestamp comment '更新时间'
);

ALTER TABLE clients ADD CONSTRAINT clients_UN UNIQUE KEY (clientId) ;


-- clients 测试数据 密码abc123
INSERT INTO clients (id, clientId, resourceIds, isSecretRequired, clientSecret, isScoped, scope, authorizedGrantTypes, registeredRedirectUri, authorities, isAutoApprove, accessTokenValiditySeconds, refreshTokenValiditySeconds, createTime, modifyTime)VALUES ('ABCD0', 'admin', 'testOauth', true, '$2a$10$zB3rjbPG6KseclyOyllbp.cy0uM/v5/gg9cO3VBEfb5ejC4p7jPSO', true, 'read,write', 'refresh_token,authorization_code,password', null, 'ROLE_ADMIN', false, 1800, 36000, '2018-12-19 10:00:00', '2018-12-19 10:00:00');
INSERT INTO clients (id, clientId, resourceIds, isSecretRequired, clientSecret, isScoped, scope, authorizedGrantTypes, registeredRedirectUri, authorities, isAutoApprove, accessTokenValiditySeconds, refreshTokenValiditySeconds, createTime, modifyTime)VALUES ('ABCD1', 'client_r', 'testOauth', true, '$2a$10$zB3rjbPG6KseclyOyllbp.cy0uM/v5/gg9cO3VBEfb5ejC4p7jPSO', true, 'read', 'refresh_token,authorization_code,password', null, 'ROLE_CLIENT', false, 1800, 36000, '2018-12-19 10:00:00', '2018-12-19 10:00:00');
INSERT INTO clients (id, clientId, resourceIds, isSecretRequired, clientSecret, isScoped, scope, authorizedGrantTypes, registeredRedirectUri, authorities, isAutoApprove, accessTokenValiditySeconds, refreshTokenValiditySeconds, createTime, modifyTime)VALUES ('ABCD2', 'client_w', 'testOauth', true, '$2a$10$zB3rjbPG6KseclyOyllbp.cy0uM/v5/gg9cO3VBEfb5ejC4p7jPSO', true, 'write', 'refresh_token,authorization_code,password', null, 'ROLE_CLIENT', false, 1800, 36000, '2018-12-19 10:00:00', '2018-12-19 10:00:00');


create table member (
  id int(11) primary key,
  userName varchar(100) not null,
  userPwd varchar(255) not null,
  userRole varchar(20) not null,
	non_expired tinyint(1),
	non_locked tinyint(1),
	credentials_non_expired tinyint(1),
	enabled tinyint(1),
  createTime timestamp default current_timestamp comment '创建时间',
  modifyTime timestamp default current_timestamp on update current_timestamp comment '更新时间'
);
ALTER TABLE member ADD CONSTRAINT member_UN UNIQUE KEY (userName) ;
INSERT INTO member (id, userName, userPwd, userRole, non_expired, non_locked, credentials_non_expired, enabled, createTime, modifyTime) VALUES(1, 'admin', '$2a$10$aHEaP4O/SM/iEfd42Zy3tuw1PTOnN7EjmiwPzVJ0yFOgwTnP4fBka', 'ADMIN_ROLE', 1, 1, 1, 1,'2018-12-19 10:00:00', '2018-12-19 10:00:00');
INSERT INTO member (id, userName, userPwd, userRole, non_expired, non_locked, credentials_non_expired, enabled, createTime, modifyTime) VALUES(2, 'user', '$2a$10$aHEaP4O/SM/iEfd42Zy3tuw1PTOnN7EjmiwPzVJ0yFOgwTnP4fBka', 'USER_ROLE', 1, 1, 1, 1, '2018-12-19 10:00:00', '2018-12-19 10:00:00');