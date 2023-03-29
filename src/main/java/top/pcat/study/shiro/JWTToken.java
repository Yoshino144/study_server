package top.pcat.study.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @program: study
 * @description:
 * @author: PCat
 * @create: 2022-02-09 10:04
 **/
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}