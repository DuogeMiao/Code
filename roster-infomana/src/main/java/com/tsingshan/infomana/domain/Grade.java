package com.tsingshan.infomana.domain;

import com.tsingshan.common.annotation.Excel;
import com.tsingshan.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 级档表 level_grade
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Grade extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;

	/** 级别 */
	@Excel(name="级别")
	private Integer level;

	/** 档别 */
	@Excel(name="档别")
	private Integer grade;

	/** 基本薪资 */
	@Excel(name="基本薪资")
	private BigDecimal baseSalary;

	/** 职务薪资 */
	@Excel(name="职务薪资")
	private BigDecimal jobSalary;

	/** 每档增幅 */
	@Excel(name="每档增幅")
	private BigDecimal everyGradeChange;

	/** 职务名称 */
	@Excel(name="职务名称")
	private String jobName;

	/** 状态（0正常 1停用） */
	private String status;

	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;


    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("level", getLevel())
            .append("grade", getGrade())
            .append("baseSalary", getBaseSalary())
            .append("jobSalary", getJobSalary())
            .append("everyGradeChange", getEveryGradeChange())
            .append("jobName", getJobName())
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
