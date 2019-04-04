package com.erafollower.task.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @describe 密码加密
 * @auth len
 * @createTime 2019/4/3
 */
public class Encrypt {

    /**
     * 加密
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encryptStr(String str){
        if(null == str){
            throw new NullPointerException("输入参数不能为空！");
        }
        return BCrypt.hashpw(str,BCrypt.gensalt());
    }

    /**
     * 密文认证
     * @param str 需要认证的字符串
     * @param preStr 已加密过的密文
     * @return
     */
    public static boolean authentication(String str,String preStr){
        return BCrypt.checkpw(str,preStr);
    }
}
