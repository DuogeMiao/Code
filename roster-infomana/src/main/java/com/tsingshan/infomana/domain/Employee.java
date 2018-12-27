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
 * 员工表 employee
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
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

	/** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name="出生日期")
	private Date birthDate;

	/** 用户性别（0男 1女 2未知） */
	@Excel(name="性别")
	private String sex;

	/** 民族 */
	@Excel(name="民族")
	private String nation;

	/** 婚姻状况（0未婚 1已婚 2离婚 3丧偶） */
	@Excel(name="婚姻状况")
	private String maritalStatus;

	/** 籍贯 */
	@Excel(name="籍贯")
	private String nativePlace;

	/** 住址 */
	@Excel(name="住址")
	private String address;

	/** 户口类型（0农村） */
	@Excel(name="户口")
	private String residenceType;

	/** 学历 */
	@Excel(name="学历")
	private String education;

	/** 政治面貌 */
	@Excel(name="政治面貌")
	private String politicsStatus;

	/** 毕业院校 */
	@Excel(name="毕业院校")
	private String graduateInstitutions;

	/** 专业 */
	@Excel(name="专业")
	private String major;

	/** 联系电话 */
	@Excel(name="联系电话")
	private String contactNumber;

	/** 紧急电话 */
	@Excel(name="紧急电话")
	private String emergencyCall;

	/** 参加工作时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name="参加工作时间")
	private Date joinWorkDate;


	/** 职称或从业资格 */
	@Excel(name="职称或从业资格")
	private String jobTitle;

	/** 获得时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name="获得时间")
	private Date getTime;

	/** 公司ID */
	private Long companyId;

	/** 部门ID */
	private Long deptId;

	/** 岗位ID */
	private Long postId;

	/** 职务ID */
	private Long jobId;

	/** 工龄 */
	private Integer workAge;

	/** 银行卡号 */
	@Excel(name="银行卡号")
	private String bankCard;

	/** 具体开户行 */
	@Excel(name="具体开户行")
	private String accountBank;

	/** 入职日期 */
	@Excel(name="入职日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date entryDate;

	/** 状态（0正常 1离职） */
	@Excel(name="状态")
	private String status;

	/** 0已签订，1未签订 */
	@Excel(name="签订合同")
	private String contractStatus;

	/** 删除标志（0代表存在 1代表删除） */
	private String delFlag;

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("employeeId", getEmployeeId())
            .append("employeeNo", getEmployeeNo())
            .append("employeeName", getEmployeeName())
            .append("identityCard", getIdentityCard())
            .append("birthDate", getBirthDate())
            .append("sex", getSex())
            .append("nation", getNation())
            .append("maritalStatus", getMaritalStatus())
            .append("nativePlace", getNativePlace())
            .append("address", getAddress())
            .append("residenceType", getResidenceType())
            .append("education", getEducation())
            .append("politicsStatus", getPoliticsStatus())
            .append("graduateInstitutions", getGraduateInstitutions())
            .append("major", getMajor())
            .append("contactNumber", getContactNumber())
            .append("emergencyCall", getEmergencyCall())
            .append("joinWorkDate", getJoinWorkDate())
            .append("jobTitle", getJobTitle())
            .append("getTime", getGetTime())
            .append("companyId", getCompanyId())
            .append("deptId", getDeptId())
			.append("postId", getPostId())
			.append("jobId", getJobId())
			.append("workAge", getWorkAge())
            .append("bankCard", getBankCard())
            .append("accountBank", getAccountBank())
            .append("entryDate", getEntryDate())
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
