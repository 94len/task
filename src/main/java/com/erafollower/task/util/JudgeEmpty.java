package com.erafollower.task.util;

import java.util.List;

/**
 * @describe 判断是否为空
 * @auth len
 * @createTime 2019/4/8
 */
public class JudgeEmpty {

    /**
     * 判断string是否为空
     * @param str
     * @return
     */
    public static boolean stringIsEmpty(String str){
        if(null == str || str.length() == 0){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 判断list是否为空
     * @param list
     * @return
     */
    public static boolean listIsEmpty(List list){
        if(null == list || list.size() == 0){
            return true;
        }else{
            return false;
        }
    }

}
