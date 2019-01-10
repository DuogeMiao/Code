package com.tsingshan.infomana.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tsingshan.common.annotation.Excel;
import com.tsingshan.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工考核表 employee_rating_form
 * 
 * @author tsingshan
 * @date 2018-12-28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RatingForm extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;

	private Long employeeId;

	/** 制度名称 */
	@Excel(name = "制度名称")
	private String institutionName;
	/** 工号 */
    @Excel(name = "工号")
	private String employeeNo;

	/** 公司 */
    @Excel(name = "公司")
	private String companyCode;

	/** 部门 */
    @Excel(name = "部门")
	private String deptName;

	/** 被考核人 */
    @Excel(name = "被考核人")
	private String byCheckPeople;

	/** 身份证 */
    @Excel(name = "身份证")
	private String identityCard;

	/** 考核时间 */
    @Excel(name = "考核时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private String checkTime;

	/** 事件名称 */
    @Excel(name = "事件名称")
	private String incidentName;

	/** 被考核金额 */
    @Excel(name = "被考核金额")
	private BigDecimal byCheckMoney;

	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;



    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("employeeId", getEmployeeId())
            .append("institutionName", getInstitutionName())
            .append("employeeNo", getEmployeeNo())
            .append("companyCode", getCompanyCode())
            .append("deptName", getDeptName())
            .append("byCheckPeople", getByCheckPeople())
            .append("identityCard", getIdentityCard())
            .append("checkTime", getCheckTime())
            .append("incidentName", getIncidentName())
            .append("byCheckMoney", getByCheckMoney())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
