package com.hwj.basic.server.membergift.entity;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@TableName(value = "member_gift",autoResultMap = true)
public class MemberGiftEntity implements Serializable {
    private static final long serialVersionUID = -5356997993731411698L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long memberId;

    private Long giftId;

    private Integer giftType;

    private Integer status;

    private Long sourceId;

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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Integer getGiftType() {
        return giftType;
    }

    public void setGiftType(Integer giftType) {
        this.giftType = giftType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return new StringJoiner(", ", MemberGiftEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("memberId='" + memberId)
                .add("giftId='" + giftId)
                .add("giftType='" + giftType )
                .add("status='" + status )
                .add("sourceId='" + sourceId)
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
