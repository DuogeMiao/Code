package com.tsingshan.system.domain;

import com.tsingshan.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 职务表 sys_job
 * 
 * @author tsingshan
 * @date 2018-12-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Job extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 职务ID */
	private Long jobId;
	/** 职务编码 */
	private String jobCode;
	/** 职务名称 */
	private String jobName;
	/** 显示顺序 */
	private Integer jobSort;
	/** 状态（0正常 1停用） */
	private String status;



    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jobId", getJobId())
            .append("jobCode", getJobCode())
            .append("jobName", getJobName())
            .append("jobSort", getJobSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
