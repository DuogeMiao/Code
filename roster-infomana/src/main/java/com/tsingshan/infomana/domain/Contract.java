package com.tsingshan.infomana.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsingshan.common.annotation.Excel;
import com.tsingshan.common.base.BaseEntity;
import com.tsingshan.common.utils.DateUtils;
import com.tsingshan.common.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 合同表 contract
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Contract extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 合同ID */
	private Long contractId;

	/** 员工ID */
	private Long employeeId;

	/** 员工姓名 */
	@Excel(name="员工姓名")
	private String employeeName;

	/** 身份证 */
	@Excel(name="身份证")
	private String identityCard;

	/** 工号 */
	@Excel(name="工号")
	private String employeeNo;

	/** 合同编号 */
	@Excel(name="合同编号")
	private String contractNo;

	/** 入职日期 */
	@Excel(name="入职日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String entryDate;

	/** 最早入职日期 */
	@Excel(name="最早入职日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String earliestEntryDate;

	/** 意外险 */
	@Excel(name="意外险")
	private String accidentInsurance;

	/** 社会保险 */
	@Excel(name="社会保险")
	private String socialInsurance;

	/** 工伤保险 */
	@Excel(name="工伤保险")
	private String injuryInsurance;

    /** 公司 */
    @Excel(name = "公司")
    private String companyCode;

    /** 部门 */
    @Excel(name = "部门")
    private String deptName;

    /** 岗位 */
    @Excel(name = "岗位")
    private String postName;

    /** 职务 */
    @Excel(name = "职务")
    private String jobName;

	/** 银行卡号 */
	@Excel(name="银行卡号")
	private String bankCard;

	/** 具体开户行 */
	@Excel(name="具体开户行")
	private String accountBank;

	/** 试用期 */
	@Excel(name="试用期")
	private Integer trialPeriod;


	/** 合同类型 */
	@Excel(name = "合同类型")
	private String contractType;

	/** 签订日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String signDate;

	/** 到期日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String expireDate;

	/** 提醒 */
	private String note;

	/** 合同年限 */
	@Excel(name = "合同年限")
	private Integer yearLimit;

	/** 状态（0未到期 1到期） */
	private String state;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

    public String getNote() {
        try {
            if (getExpireDate() != null) {
                long daySub = DateUtils.getDaySub(DateUtils.getDate(), getExpireDate(), "yyyy-MM-dd");
                if (daySub <= 15 && daySub > 0) {
                    note = "合同还有" + daySub + "天到期";
                } else if (daySub == 0) {
                    note = "合同今天到期";
                } else if (daySub < 0) {
                    note = "合同已经到期";
                }
                note = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return note;
    }
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("contractId", getEmployeeId())
                .append("employeeId", getEmployeeNo())
                .append("employeeName", getEmployeeName())
                .append("identityCard", getIdentityCard())
                .append("entryDate", getEntryDate())
                .append("contractNo", getContractNo())
                .append("earliestEntryDate", getEarliestEntryDate())
                .append("accidentInsurance", getAccidentInsurance())
                .append("socialInsurance", getSocialInsurance())
                .append("injuryInsurance", getInjuryInsurance())
                .append("companyCode", getCompanyCode())
                .append("deptName", getDeptName())
                .append("postName", getPostName())
                .append("jobName", getJobName())
                .append("bankCard", getBankCard())
                .append("accountBank", getAccountBank())
                .append("trialPeriod", getTrialPeriod())
                .append("contractType", getContractType())
                .append("signDate", getSignDate())
                .append("expireDate", getExpireDate())
                .append("note", getNote())
                .append("yearLimit", getYearLimit())
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
