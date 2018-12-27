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
 * 晋升表 promotion
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Promotion extends BaseEntity
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
	@Excel(name="公司名称")
	private String companyName;

	/** 部门名称 */
	@Excel(name="部门名称")
	private String deptName;

	/** 职务名称 */
	@Excel(name="职务名称")
	private String jobName;

	/** 岗位名称 */
	@Excel(name="岗位名称")
	private String postName;

	/** 执行日期 */
	@Excel(name="执行日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date execDate;

	/** 晋升次数 */
	@Excel(name="晋升次数")
	private Integer counts;

	/** 回传日期 */
	@Excel(name="回传日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date backDate;

	/** 晋升类型（升、降） */
	@Excel(name="晋升类型")
	private String promotionType;

	/** 状态（0正常 1停用） */
	private String status;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("employeeId", getEmployeeId())
            .append("employeeNo", getEmployeeNo())
            .append("employeeName", getEmployeeName())
            .append("identityCard", getIdentityCard())
            .append("companyName", getCompanyName())
            .append("deptName", getDeptName())
            .append("jobName", getJobName())
            .append("postName", getPostName())
            .append("execDate", getExecDate())
            .append("counts", getCounts())
            .append("backDate", getBackDate())
            .append("promotionType", getPromotionType())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
