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
 * 护照签证表 passport_visa
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Visa extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 员工ID */
	private Long employeeId;

	/** 护照号 */
	@Excel(name="护照号")
	private String passportNo;

	/** 工号 */
	@Excel(name="工号")
	private String employeeNo;

	/** 员工姓名 */
	@Excel(name="员工姓名")
	private String employeeName;

	/** 身份证 */
	@Excel(name="身份证")
	private String identityCard;

	/** 护照签发日期 */
	@Excel(name="护照签发日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date passportIssueDate;

	/** 护照截止日期 */
	@Excel(name="护照截止日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date passportDeadline;

	/** 签证编号 */
	@Excel(name="签证编号")
	private String visaNo;

	/** 签证类型 */
	@Excel(name="签证类型")
	private String visaType;

	/** 签证有效期（天） */
	@Excel(name="签证有效期")
	private Integer visaValidity;

	/** 入境时间 */
	@Excel(name="入境时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date entryTime;

	/** 处境时间 */
	@Excel(name="处境时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date exitTime;

	/** 入矿时间 */
	@Excel(name="入矿时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date inMineTime;

	/** 出矿时间 */
	@Excel(name="出矿时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date outMineTime;

	/** 状态（0正常 1停用） */
	private String status;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("employeeId", getEmployeeId())
            .append("passportNo", getPassportNo())
            .append("employeeNo", getEmployeeNo())
            .append("employeeName", getEmployeeName())
            .append("identityCard", getIdentityCard())
            .append("passportIssueDate", getPassportIssueDate())
            .append("passportDeadline", getPassportDeadline())
            .append("visaNo", getVisaNo())
            .append("visaType", getVisaType())
            .append("visaValidity", getVisaValidity())
            .append("entryTime", getEntryTime())
            .append("exitTime", getExitTime())
            .append("inMineTime", getInMineTime())
            .append("outMineTime", getOutMineTime())
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
