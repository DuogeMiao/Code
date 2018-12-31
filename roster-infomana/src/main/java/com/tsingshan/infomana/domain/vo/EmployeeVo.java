package com.tsingshan.infomana.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tsingshan.common.annotation.Excel;
import com.tsingshan.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeVo extends BaseEntity{

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

    /** 公司 */
    @Excel(name="公司")
    private String companyCode;

    /** 部门ID */
    @Excel(name="部门")
    private String deptName;

    /** 岗位ID */
    @Excel(name="岗位")
    private String postName;

    /** 职务ID */
    @Excel(name="职务")
    private String jobName;

    /** 工龄 */
    @Excel(name="工龄")
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

    /** 状态（0在职 1离职） */
    @Excel(name="在职状态")
    private String state;

    @Excel(name="签订合同")
    private String contractStatus;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;


}
