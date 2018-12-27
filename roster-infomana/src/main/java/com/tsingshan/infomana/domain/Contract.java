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
	private Date entryDate;

	/** 最早入职日期 */
	@Excel(name="最早入职日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date earliestEntryDate;

	/** 意外险 */
	@Excel(name="意外险")
	private String accidentInsurance;

	/** 社会保险 */
	@Excel(name="社会保险")
	private String socialInsurance;

	/** 工伤保险 */
	@Excel(name="工伤保险")
	private String injuryInsurance;

    /** 公司ID */
    private Long companyId;

    /** 部门ID */
    private Long deptId;

    /** 岗位ID */
    private Long postId;

    /** 职务ID */
    private Long jobId;

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
	private String contractType;

	/** 签订日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date signDate;

	/** 到期日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expireDate;

	/** 提醒 */
	private String note;

	/** 合同年限 */
	private Integer yearLimit;

	/** 状态（0未到期 1到期） */
	private String status;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

    public String getNote() {
        long nowTime = new Date().getTime();
        if (getExpireDate() != null) {
            long expireTime = getExpireDate().getTime();
            long day = (expireTime - nowTime)/(24*60*60*1000);
            if (day <= 15 && day >= 0 ) {
                note = "合同到期，还有"+ day + "天";
            }
        }
        return note;
    }
}
