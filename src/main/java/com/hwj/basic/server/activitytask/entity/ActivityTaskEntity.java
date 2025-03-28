package com.hwj.basic.server.activitytask.entity;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@TableName(value = "activity_task_config")
public class ActivityTaskEntity implements Serializable {

    private static final long serialVersionUID = -8692701555117356640L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活动任务名称
     */
    private String name;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 消息事件code
     */
    private String msgEventCode;

    /**
     * 奖励配置
     */
    private String rewardConfig;

    /**
     * 任务规则配置
     */
    private String ruleConfig;

    /**
     * 任务状态[0:草稿 1:上线 2:下线]
     */
    private Integer status;

    /**
     * 扩展字段
     */
    private JSONObject features;

    /**
     * 乐观锁版本号
     */
    @Version
    private Integer lockVersion;

    /**
     * 是否删除[否:0 是:记录id]
     */
    private boolean deleted;

    /**
     * 删除时间
     */
    private LocalDateTime deletedDate;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedDate;

    /**
     * 更新人
     */
    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getMsgEventCode() {
        return msgEventCode;
    }

    public void setMsgEventCode(String msgEventCode) {
        this.msgEventCode = msgEventCode;
    }

    public String getRewardConfig() {
        return rewardConfig;
    }

    public void setRewardConfig(String rewardConfig) {
        this.rewardConfig = rewardConfig;
    }

    public String getRuleConfig() {
        return ruleConfig;
    }

    public void setRuleConfig(String ruleConfig) {
        this.ruleConfig = ruleConfig;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public JSONObject getFeatures() {
        return features;
    }

    public void setFeatures(JSONObject features) {
        this.features = features;
    }

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ActivityTaskEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("activityId='" + activityId)
                .add("msgEventCode='" + msgEventCode + "'")
                .add("rewardConfig='" + rewardConfig + "'")
                .add("ruleConfig='" + ruleConfig + "'")
                .add("status='" + status)
                .add("features='" + features)
                .add("lockVersion=" + lockVersion)
                .add("deleted=" + deleted)
                .add("deletedDate=" + deletedDate)
                .add("createdDate=" + createdDate)
                .add("createdBy="+ createdBy + "'")
                .add("updatedDate=" + updatedDate)
                .add("updatedBy=" + updatedBy + "'")
                .toString();
    }
}
