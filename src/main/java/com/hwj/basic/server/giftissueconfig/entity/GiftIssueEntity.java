package com.hwj.basic.server.giftissueconfig.entity;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @Program: hwj
 * @Description: 礼品发放流水实体类
 * @author: wenjing.huang
 * @since: 2025-03-17 15:03:24
 */
@TableName(value = "gift_issue_log", autoResultMap = true)
public class GiftIssueEntity implements Serializable {

    private static final long serialVersionUID = 6704694315173465700L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long memberId;

    /**
     * 礼品信息列表
     */
    private String giftInfos;

    /**
     * 处理状态[0:待处理 1:处理中 2:处理成功 3: 处理失败]
     */
    private Integer processStatus;

    /**
     * 来源类型
     */
    private Integer sourceType;

    /**
     * 来源id
     */
    private Long sourceId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除[否:0 是:记录id]
     */
    private Long deleted;

    /**
     * 删除时间
     */
    private LocalDateTime deletedDate;

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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getGiftInfos() {
        return giftInfos;
    }

    public void setGiftInfos(String giftInfos) {
        this.giftInfos = giftInfos;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getDeleted() {
        return deleted;
    }

    public void setDeleted(Long deleted) {
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
        return new StringJoiner(", ", GiftIssueEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("memberId=" + memberId)
                .add("giftInfos='" + giftInfos + "'")
                .add("processStatus=" + processStatus)
                .add("sourceType=" + sourceType)
                .add("sourceId=" + sourceId)
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
