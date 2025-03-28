package com.hwj.basic.server.activityrule.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@TableName(value = "activity_rule_text",autoResultMap = true)
public class ActivityRuleTextEntity implements Serializable {

    private static final long serialVersionUID = -7486606677910998072L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long activityId;

    private String ruleText;

    private boolean deleted;

    private LocalDateTime deletedDate;

    @Version
    private Integer lockVersion;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    private String createdBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedDate;

    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getRuleText() {
        return ruleText;
    }

    public void setRuleText(String ruleText) {
        this.ruleText = ruleText;
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

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDaate(LocalDateTime createdDaate) {
        this.createdDate = createdDaate;
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
        return new StringJoiner(", ", ActivityRuleTextEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("activityId='" + activityId)
                .add("ruleText='" + ruleText + "'")
                .add("deleted='" + deleted)
                .add("deletedDate='" + deletedDate)
                .add("lockVersion='" + lockVersion)
                .add("createdDate='" + createdDate)
                .add("createdBy='" + createdBy + "'")
                .add("updatedDate=" + updatedDate)
                .add("updatedBy=" + updatedBy + "'")
                .toString();
    }
}
