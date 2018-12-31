package com.tsingshan.infomana.domain;

import com.tsingshan.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 薪资级档表 salary_level_grade
 * 
 * @author tsingshan
 * @date 2018-12-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LevelGrade extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;

	/** 级档名称 */
	private String levelGradeName;

    /** 父菜单名称 */
    private String parentName;

	/** 父菜单ID */
	private Integer parentId;
	/** 显示顺序 */
	private Integer sortOrder;

	/** 基本薪资 */
	private BigDecimal baseSalary;

	/** 职务薪资 */
	private BigDecimal jobSalary;

	/** 每档增幅 */
	private BigDecimal everyGradeChange;

	/** 职务名称 */
	private String jobName;

	/** 级档类型（L级 G档） */
	private String levelGradeType;

	/** 状态。可选值:1(正常),2(删除) */
	private Integer state;

	/** 图标 */
	private String icon;

	/** 该类目是否为父类目，1为true，0为false */
	private Boolean isParent;

    /** 子菜单 */
	private List<LevelGrade> children = new ArrayList<LevelGrade>();


    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getLevelGradeName())
            .append("parentId", getParentId())
            .append("sortOrder", getSortOrder())
            .append("baseSalary", getBaseSalary())
            .append("jobSalary", getJobSalary())
            .append("everyGradeChange", getEveryGradeChange())
            .append("jobName", getJobName())
            .append("levelGradeType", getLevelGradeType())
            .append("state", getState())
            .append("icon", getIcon())
            .append("isParent", getIsParent())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
