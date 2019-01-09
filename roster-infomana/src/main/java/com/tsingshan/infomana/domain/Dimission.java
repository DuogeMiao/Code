package com.tsingshan.infomana.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tsingshan.common.annotation.Excel;
import com.tsingshan.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 离职表 dimission
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Dimission extends BaseEntity
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
	@Excel(name="部门")
	private String deptName;

	/** 岗位名称 */
	@Excel(name="岗位")
	private String jobName;

	/** 职务名称 */
	@Excel(name="职务")
	private String postName;

	/** 入职日期 */
	@Excel(name="入职日期")
	private String entryDate;

	/** 离职日期 */
	@Excel(name="离职日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String dimissionDate;

	/** 回传日期 */
	@Excel(name="回传日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String backDate;

	/** 离职类型（异动、自请、解雇、自动） */
	@Excel(name="离职类型")
	private String dimissionType;

	/** 离职原因 */
	@Excel(name="离职原因")
	private String dimissionReason;

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
            .append("entryDate", getEntryDate())
            .append("dimissionDate", getDimissionDate())
            .append("backDate", getBackDate())
            .append("dimissionType", getDimissionType())
            .append("dimissionReason", getDimissionReason())
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
