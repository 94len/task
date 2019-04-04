package com.erafollower.task.model.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author len
 * @since 2019-04-02
 */
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 登录帐号，以数字和英文字母组成
     */
    private String account;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 生成时间
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
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "SystemUser{" +
        "id=" + id +
        ", nickName=" + nickName +
        ", account=" + account +
        ", password=" + password +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        "}";
    }
}
