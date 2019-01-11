package com.tsingshan.infomana.domain;

import com.tsingshan.common.annotation.Excel;
import com.tsingshan.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 签证表 ros_visa_info
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VisaInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;

    /** 护照ID */
    private Long passportId;

    @Excel(name = "护照号")
    private String passportNo;

    @Excel(name = "姓名")
    private String employeeName;

    @Excel(name = "身份证")
    private String identityCard;

    @Excel(name = "工号")
    private String employeeNo;

	/** 签证编号 */
	private String visaNo;

	/** 签证类型 */
    @Excel(name = "签证类型")
	private String visaType;

	/** 签证有效期（天） */
    @Excel(name = "签证有效期（天）")
	private Integer visaValidity;

	/** 入境时间 */
    @Excel(name = "入境时间")
	private String entryTime;

	/** 出境时间 */
    @Excel(name = "出境时间")
	private String exitTime;

	/** 入矿时间 */
    @Excel(name = "入矿时间")
	private String inMineTime;

	/** 出矿时间 */
    @Excel(name = "出矿时间")
	private String outMineTime;

	/** 状态（0正常 1停用） */
	private String state;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("visaNo", getVisaNo())
            .append("visaType", getVisaType())
            .append("visaValidity", getVisaValidity())
            .append("entryTime", getEntryTime())
            .append("exitTime", getExitTime())
            .append("inMineTime", getInMineTime())
            .append("outMineTime", getOutMineTime())
            .append("passportId", getPassportId())
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
