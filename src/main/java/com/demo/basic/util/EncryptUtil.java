package com.demo.basic.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加解密工具
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class EncryptUtil {
    /**
     * 參考網址
     * https://blog.csdn.net/qq_37170583/article/details/80704660
     *
     * 取得Spring security需要的加密後字串
     * secret密码配置从 Spring Security 5.0开始必须以 {加密方式}+加密后的密码 这种格式填写
     * 当前版本5新增支持加密方式：
     * bcrypt - BCryptPasswordEncoder (Also used for encoding)
     * ldap - LdapShaPasswordEncoder
     * MD4 - Md4PasswordEncoder
     * MD5 - new MessageDigestPasswordEncoder(“MD5”)
     * noop - NoOpPasswordEncoder
     * pbkdf2 - Pbkdf2PasswordEncoder
     * scrypt - SCryptPasswordEncoder
     * SHA-1 - new MessageDigestPasswordEncoder(“SHA-1”)
     * SHA-256 - new MessageDigestPasswordEncoder(“SHA-256”)
     * sha256 - StandardPasswordEncoder
     *
     * @param str
     * @return
     */
    public static String getSecurityPwd(String str) {
        return "{bcrypt}" + new BCryptPasswordEncoder().encode(str);
    }
}
