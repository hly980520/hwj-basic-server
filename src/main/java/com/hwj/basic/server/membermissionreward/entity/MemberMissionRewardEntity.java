package com.hwj.basic.server.membermissionreward.entity;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@TableName(value = "member_mission_reward",autoResultMap = true)
public class MemberMissionRewardEntity implements Serializable {
    private static final long serialVersionUID = -675286595336074294L;

    /**
     * 业务ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 活动任务ID
     */
    private Long activityTaskId;

    /**
     * 用户ID
     */
    private Long memberId;

    /**
     * 奖励id列表
     */
    private String rewardIds;

    /**
     * 发放方式[1:自动发放,2:手动发放]
     */
    private Integer sendType;

    /**
     * 发放状态[1:待发放,2:发放中,3:已发放,4:发放失败]
     */
    private Integer sendStatus;

    /**
     * 发放时间
     */
    private LocalDateTime sendTime;

    /**
     * 来源id
     */
    private Long sourceId;

    /**
     * 备注
     */
    private String remark;

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
    private Long deleted;

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

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityTaskId() {
        return activityTaskId;
    }

    public void setActivityTaskId(Long activityTaskId) {
        this.activityTaskId = activityTaskId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getRewardIds() {
        return rewardIds;
    }

    public void setRewardIds(String rewardIds) {
        this.rewardIds = rewardIds;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
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
        return new StringJoiner(", ", MemberMissionRewardEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("activityTaskId=" + activityTaskId)
                .add("memberId=" + memberId)
                .add("rewardIds='" + rewardIds + "'")
                .add("sendType=" + sendType)
                .add("sendStatus=" + sendStatus)
                .add("sendTime=" + sendTime)
                .add("sourceId=" + sourceId)
                .add("remark='" + remark + "'")
                .add("features=" + features)
                .add("lockVersion=" + lockVersion)
                .add("deleted=" + deleted)
                .add("deletedDate=" + deletedDate)
                .add("createdDate=" + createdDate)
                .add("createdBy='" + createdBy + "'")
                .add("updatedDate=" + updatedDate)
                .add("updatedBy='" + updatedBy + "'")
                .toString();
    }

}
