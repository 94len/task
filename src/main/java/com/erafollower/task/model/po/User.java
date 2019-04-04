package com.erafollower.task.model.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author len
 * @since 2019-04-02
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 微信号
     */
    private String wxNum;

    /**
     * 邮箱帐号
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getWxNum() {
        return wxNum;
    }

    public void setWxNum(String wxNum) {
        this.wxNum = wxNum;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", name=" + name +
        ", nickName=" + nickName +
        ", phoneNum=" + phoneNum +
        ", wxNum=" + wxNum +
        ", email=" + email +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        "}";
    }
}
