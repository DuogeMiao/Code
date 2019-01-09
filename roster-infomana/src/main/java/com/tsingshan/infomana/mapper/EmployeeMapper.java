package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.Employee;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 员工 数据层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface EmployeeMapper 
{
	/**
     * 查询员工信息
     * 
     * @param employeeId 员工ID
     * @return 员工信息
     */
	public Employee selectEmployeeById(Long employeeId);

	/**
	 * 工号查询
	 * @param employeeNo
	 * @return
	 */
	Employee selectEmployeeByEmployeeNo(String employeeNo);
	
	/**
     * 查询员工列表
     * 
     * @param employee 员工信息
     * @return 员工集合
     */
	List<Employee> selectEmployeeList(Employee employee);

    /**
     * 根据员工在职状态查询
     * @param state
     * @return
     */
	List<Employee> selectEmployeeListByStatus(@Param("state") String state);

    /**
     * 根据 员工在职状态 与 签订合同状态查询
     * @param state
     * @param contractStatus
     * @return
     */
    List<Employee> selectEmployeeListByStateAndContractStatus(@Param("state") String state, @Param("contractStatus") String contractStatus);

	/**
     * 新增员工
     *
     * @param employee 员工信息
     * @return 结果
     */
	int insertEmployee(Employee employee);

    /**
     * 批量插入
     * @param employeeList
     * @return
     */
	int batchInsertEmployee(@Param("list") List<Employee> employeeList);

	/**
     * 修改员工
     *
     * @param employee 员工信息
     * @return 结果
     */
	public int updateEmployee(Employee employee);

	/**
     * 删除员工
     *
     * @param employeeId 员工ID
     * @return 结果
     */
	public int deleteEmployeeById(Long employeeId);

	/**
     * 批量删除员工
     *
     * @param employeeIds 需要删除的数据ID
     * @return 结果
     */
	int deleteEmployeeByIds(Long[] employeeIds);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Employee> selectExport(@Param("ids") Long[] ids);

}