package com.erafollower.task.model.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author len
 * @since 2019-04-02
 */
public class TaskRemind implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务ID
     */
    private Integer taskId;

    /**
     * 提醒时间
     */
    private Date remindTime;

    private Date createTime;

    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
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
        return "TaskRemind{" +
        "id=" + id +
        ", taskId=" + taskId +
        ", remindTime=" + remindTime +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        "}";
    }
}
