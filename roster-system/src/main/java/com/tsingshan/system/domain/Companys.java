package com.tsingshan.system.domain;

import com.tsingshan.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 公司表 companys
 * 
 * @author tsingshan
 * @date 2018-12-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Companys extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 岗位ID */
	private Long companyId;
	/** 岗位编码 */
	private String companyCode;
	/** 岗位名称 */
	private String companyName;
	/** 显示顺序 */
	private Integer companySort;
	/** 状态（0正常 1停用） */
	private String status;


	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("companyId", getCompanyId())
            .append("companyCode", getCompanyCode())
            .append("companyName", getCompanyName())
            .append("companySort", getCompanySort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
