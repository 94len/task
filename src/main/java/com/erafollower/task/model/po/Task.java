package com.erafollower.task.model.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 任务表
 * </p>
 *
 * @author len
 * @since 2019-04-03
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 任务内容
     */
    private String content;

    /**
     * 任务图片路径（最多存三张）
     */
    private String pic;

    /**
     * 提醒时间
     */
    private Date remindTime;

    /**
     * 提醒方式(可多选)
1：邮件。2：短信。3：微信（预留方式）
  同时以邮箱和短信方式提醒 eg: {1,2}
     */
    private String remindWay;

    private Date createTime;

    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }
    public String getRemindWay() {
        return remindWay;
    }

    public void setRemindWay(String remindWay) {
        this.remindWay = remindWay;
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
        return "Task{" +
        "id=" + id +
        ", userId=" + userId +
        ", content=" + content +
        ", pic=" + pic +
        ", remindTime=" + remindTime +
        ", remindWay=" + remindWay +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        "}";
    }
}
