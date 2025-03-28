package com.hwj.basic.server.messageeventconfig.entity;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@TableName(value = "message_event_config",autoResultMap = true)
public class MessageEventEntity implements Serializable {

    private static final long serialVersionUID = 5599060968689474086L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * 事件代码
     */
    private String eventCode;

    /**
     * 消息模板代码
     */
    private String messageTemplateCode;

    /**
     * 接收人类型[0:指定用户,1:指定手机号]
     */
    private Integer receiveType;

    /**
     * 状态[0:禁用,1:启用]
     */
    private Integer status;

    private String remark;

    private Boolean deleted;

    private LocalDateTime deletedDate;

    private JSONObject features;

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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getMessageTemplateCode() {
        return messageTemplateCode;
    }

    public void setMessageTemplateCode(String messageTemplateCode) {
        this.messageTemplateCode = messageTemplateCode;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
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
        return new StringJoiner(", ", MessageEventEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("eventName='" + eventName + "'")
                .add("eventCode='" + eventCode + "'")
                .add("messageTemplateCode='" + messageTemplateCode + "'")
                .add("receiveType='" + receiveType)
                .add("status='" + status)
                .add("remark='" + remark + "'")
                .add("deleted=" + deleted)
                .add("deletedDate=" + deletedDate)
                .add("features=" + features)
                .add("lockVersion=" + lockVersion)
                .add("createdDate=" + createdDate)
                .add("createdBy='" + createdBy + "'")
                .add("updatedDate=" + updatedDate)
                .add("updatedBy='" + updatedBy + "'")
                .toString();
    }
}
