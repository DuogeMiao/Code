package com.tsingshan.infomana.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tsingshan.common.annotation.Excel;
import com.tsingshan.common.base.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 调薪表 adjustment_salary
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Data
public class Salary extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 员工ID */
	private Long employeeId;

	/** 工号 */
	@Excel(name="工号")
	private String employeeNo;

	/** 员工姓名 */
	@Excel(name="员工姓名")
	private String employeeName;

	/** 身份证 */
	@Excel(name="身份证")
	private String identityCard;

	/** 公司名称 */
	@Excel(name="公司")
	private String companyCode;

	/** 部门名称 */
	@Excel(name="部门名称")
	private String deptName;

	/** 职务名称 */
	@Excel(name="职务名称")
	private String jobName;

	/** 岗位名称 */
	@Excel(name="岗位名称")
	private String postName;

	/** 基本薪资 */
	@Excel(name="基本薪资")
	private BigDecimal baseSalary;

	/** 职务薪资 */
	@Excel(name="职务薪资")
	private BigDecimal jobSalary;

	/** 绩效 */
	@Excel(name="绩效")
	private BigDecimal performance;

	/** 回传日期 */
	@Excel(name="回传日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String backDate;

	/** 调整次数 */
	private Integer counts;

	/** 类型（晋升、转正、调薪） */
	@Excel(name="类型")
	private String adjustmentType;

	/** 补贴 */
	@Excel(name="补贴")
	private BigDecimal subsidy;

	/** 调薪日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String adjustmentDate;

	/** 生效日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String effectiveDate;
	/** 状态（0正常 1停用） */
	private String state;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("employeeId", getEmployeeId())
            .append("employeeNo", getEmployeeNo())
            .append("employeeName", getEmployeeName())
            .append("identityCard", getIdentityCard())
            .append("companyCode", getCompanyCode())
            .append("deptName", getDeptName())
            .append("jobName", getJobName())
            .append("postName", getPostName())
            .append("baseSalary", getBaseSalary())
            .append("jobSalary", getJobSalary())
            .append("performance", getPerformance())
            .append("backDate", getBackDate())
            .append("counts", getCounts())
            .append("adjustmentType", getAdjustmentType())
            .append("subsidy", getSubsidy())
            .append("adjustmentDate", getAdjustmentDate())
            .append("effectiveDate", getEffectiveDate())
            .append("state", getState())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
