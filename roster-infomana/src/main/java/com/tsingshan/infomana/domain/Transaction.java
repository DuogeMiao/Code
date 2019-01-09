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
 * 异动表 transaction
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity
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

	/** 异动出公司 */
	@Excel(name="异动出公司")
	private String transOutCompany;

	/** 异动出部门 */
	@Excel(name="异动出部门")
	private String transOutDept;

	/** 异动出岗位 */
	@Excel(name="异动出岗位")
	private String transOutPost;

	/** 异动入公司 */
	@Excel(name="异动入公司")
	private String transInCompany;

	/** 异动入部门 */
	@Excel(name="异动入部门")
	private String transInDept;

	/** 异动入岗位 */
	@Excel(name="异动入岗位")
	private String transInPost;

	/** 是否跨公司 0-是、1-否 */
	@Excel(name="是否跨公司")
	private String spanCompany;

	/** 生效日期 */
	@Excel(name="生效日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String effectiveDate;

	/** 回传日期 */
	@Excel(name="回传日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String backDate;

	/** 异动次数 */
	private Integer counts;

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
            .append("transOutCompany", getTransOutCompany())
            .append("transOutDept", getTransOutDept())
            .append("transOutPost", getTransOutPost())
            .append("transInCompany", getTransInCompany())
            .append("transInDept", getTransInDept())
            .append("transInPost", getTransInPost())
            .append("spanCompany", getSpanCompany())
            .append("effectiveDate", getEffectiveDate())
            .append("backDate", getBackDate())
            .append("counts", getCounts())
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
