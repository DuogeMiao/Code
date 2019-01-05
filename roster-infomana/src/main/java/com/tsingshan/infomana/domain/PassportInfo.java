package com.tsingshan.infomana.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class PassportInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 护照号 */
	private String passportNo;
	/** 护照签发日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date passportIssueDate;
	/** 护照截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date passportDeadline;
	/** 员工ID */
	private Long employeeId;
	/** 状态（0正常 1停用） */
	private String state;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("passportNo", getPassportNo())
            .append("passportIssueDate", getPassportIssueDate())
            .append("passportDeadline", getPassportDeadline())
            .append("employeeId", getEmployeeId())
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
