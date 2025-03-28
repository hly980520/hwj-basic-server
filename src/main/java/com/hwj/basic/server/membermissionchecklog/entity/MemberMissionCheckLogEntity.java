package com.hwj.basic.server.membermissionchecklog.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@TableName(value = "member_mission_check_log",autoResultMap = true)
public class MemberMissionCheckLogEntity implements Serializable {

    private static final long serialVersionUID = 8962702939773888333L;


    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 任务id
     */
    private Long missionId;

    /**
     * 用户id
     */
    private Long memberId;

    /**
     * 达标:[0:不达标,1:已达标]
     */
    private Integer qualified;

    /**
     * 备注
     */
    private String remark;

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

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getQualified() {
        return qualified;
    }

    public void setQualified(Integer qualified) {
        this.qualified = qualified;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return new StringJoiner(", ", MemberMissionCheckLogEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("missionId=" + missionId)
                .add("memberId=" + memberId)
                .add("qualified=" + qualified)
                .add("remark='" + remark + "'")
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
