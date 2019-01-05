package com.tsingshan.infomana.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tsingshan.common.annotation.Excel;
import com.tsingshan.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 护照表 ros_passport_info
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PassportInfoVo extends BaseEntity
{
	private static final long serialVersionUID = 1L;

    /** ID */
	private Long id;

    /** 员工ID */
    private Long employeeId;

    /** 护照号 */
    @Excel(name = "护照号")
	private String passportNo;

    /** 姓名 */
    @Excel(name = "姓名")
    private String employeeName;

    /** 身份证 */
    @Excel(name = "身份证")
    private String identityCard;

    /** 工号 */
    @Excel(name = "工号")
    private String employeeNo;

    /** 护照签发日期 */
    @Excel(name = "护照签发日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date passportIssueDate;

    /** 护照截止日期 */
    @Excel(name = "护照截止日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date passportDeadline;

	/** 状态（0正常 1停用） */
	private String state;

	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

}
