package com.tsingshan.infomana.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tsingshan.common.annotation.Excel;
import com.tsingshan.common.base.BaseEntity;
import com.tsingshan.common.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 合同表 contract
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractVo extends BaseEntity
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
    private String companyCode;

    /** 部门ID */
    private String deptName;

    /** 岗位ID */
    private String postName;

    /** 职务ID */
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
	private String contractType;

	/** 签订日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date signDate;

	private Integer yearLimit;

	/** 到期日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expireDate;

	private String note;

	/** 状态（0未到期 1到期） */
	private String state;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;


    public String getNote() {
        try {
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(new Date());
            Date date = sdf.parse(format);
            long nowTime = date.getTime();
            if (getExpireDate() != null) {
                long expireTime = getExpireDate().getTime();
                long day = (expireTime - nowTime)/(24*60*60*1000) ;
                if (day <= 15 && day >= 0 ) {
                    note = "合同还有"+ day + "天到期";
                }
                else {
                    note = "已过期";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return note;

    }
//    public static void main (String[] args) {
//        try {
//            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//            String format = sdf.format(new Date());
//            Date parse = sdf.parse(format);
//            System.out.println("------ " +parse.getTime());
//            long nowTime = new Date().getTime();
//            System.out.println("====== " + nowTime);
//            long nowTime = new Date().getTime();
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//
//        }
//
//
//    }

}
