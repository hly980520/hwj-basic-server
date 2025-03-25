package com.hwj.basic.server.memberoperlog.entity;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@TableName(value = "member_oper_log",autoResultMap = true)
public class MemberOperEntity implements Serializable {

    private static final long serialVersionUID = 8276328086657228897L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long memberId;

    private String loginAccount;

    private String operationType;

    private String memberRealIp;

    private Boolean deleted;

    private LocalDateTime deletedDate;

    private JSONObject features;

    @Version
    private Integer lockVersion;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    private String createdBy;

    @TableField(fill = FieldFill.INSERT)
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

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getMemberRealIp() {
        return memberRealIp;
    }

    public void setMemberRealIp(String memberRealIp) {
        this.memberRealIp = memberRealIp;
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
        return new StringJoiner(", ", MemberOperEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("memberId='" + memberId)
                .add("loginAccount='" + loginAccount + "'")
                .add("operationType='" + operationType + "'")
                .add("memberRealIp='" + memberRealIp + "'")
                .add("deleted='" + deleted)
                .add("deletedDate='" + deletedDate)
                .add("features='" + features)
                .add("lockVersion=" + lockVersion)
                .add("createdDate=" + createdDate)
                .add("createdBy=" + createdBy + "'")
                .add("updatedDate=" + updatedDate)
                .add("updatedBy='" + updatedBy + "'")
                .toString();
    }
}
