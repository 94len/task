package com.erafollower.task.shiro;

import com.erafollower.task.util.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.mindrot.jbcrypt.BCrypt;

/**
 * 自定义密码比较器
 */
public class PasswordMatcher extends SimpleCredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;

        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        String username = utoken.getUsername();

        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();


        //进行密码的比对
        boolean flag = Encrypt.authentication(inPassword,dbPassword);
        return flag;
    }

}
