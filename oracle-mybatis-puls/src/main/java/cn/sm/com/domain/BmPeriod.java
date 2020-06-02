package cn.sm.com.domain;

import java.math.BigDecimal;

import cn.sm.com.base.EntityBase;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 预算期间表
 * </p>
 *
 * @author xubo
 * @since 2020-05-30
 */
@TableName("BM_PERIOD")
public class BmPeriod extends EntityBase<BmPeriod> {

    private static final long serialVersionUID=1L;

    /**
     * period_id
     */
    @TableId("PERIOD_ID")
    private BigDecimal periodId;

    /**
     * 期间名称
     */
    @TableField("PERIOD_NAME")
    private String periodName;

    /**
     * 父节点id
     */
    @TableField("PARENT_ID")
    private BigDecimal parentId;

    /**
     * 开始时间
     */
    @TableField("START_TIME")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField("END_TIME")
    private LocalDateTime endTime;

    /**
     * 所属财年
     */
    @TableField("YEAR")
    private Integer year;

    /**
     * 序列值
     */
    @TableField("SEQUENCE")
    private BigDecimal sequence;

    /**
     * 存在子节点
     */
    @TableField("HAS_CHILDREN")
    private String hasChildren;

    /**
     * 是否可用
     */
    @TableField("IS_ENABLE")
    private String isEnable;

    /**
     * 季度(1/2/3/4/0)
     */
    @TableField("QUARTER")
    private Integer quarter;


    public BigDecimal getPeriodId() {
        return periodId;
    }

    public void setPeriodId(BigDecimal periodId) {
        this.periodId = periodId;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public BigDecimal getParentId() {
        return parentId;
    }

    public void setParentId(BigDecimal parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getSequence() {
        return sequence;
    }

    public void setSequence(BigDecimal sequence) {
        this.sequence = sequence;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    @Override
    protected Serializable pkVal() {
        return this.periodId;
    }

    @Override
    public String toString() {
        return "BmPeriod{" +
        "periodId=" + periodId +
        ", periodName=" + periodName +
        ", parentId=" + parentId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", year=" + year +
        ", sequence=" + sequence +
        ", hasChildren=" + hasChildren +
        ", isEnable=" + isEnable +
        ", quarter=" + quarter +
        "}";
    }
}
