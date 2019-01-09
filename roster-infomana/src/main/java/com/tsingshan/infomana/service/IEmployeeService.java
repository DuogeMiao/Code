package com.tsingshan.infomana.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.infomana.domain.Employee;

import java.util.List;

/**
 * 员工 服务层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface IEmployeeService 
{
	/**
     * 查询员工信息
     * 
     * @param employeeId 员工ID
     * @return 员工信息
     */
	Employee selectEmployeeById(long employeeId);
	/**
     * 查询员工列表
     * 
     * @param employee 员工信息
     * @return 员工集合
     */
	List<Employee> selectEmployeeList(Employee employee);


	List<Employee> selectEmployeeListByState(String state);

	List<Employee> selectEmployeeListByStateAndContractStatus(String state,String contractStatus);



	/**
     * 新增员工
     * 
     * @param employee 员工信息
     * @return 结果
     */
	AjaxResult insertEmployee(Employee employee);

	AjaxResult batchInsertEmployee(List<Employee> employeeList);

	/**
     * 修改员工
     * 
     * @param employee 员工信息
     * @return 结果
     */
	AjaxResult updateEmployee(Employee employee);
		
	/**
     * 删除员工信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	AjaxResult deleteEmployeeByIds(String ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Employee> selectExport(String ids);

    /**
     * 根据工号获取员工信息
     * @param employeeNo
     * @return
     */
    Employee selectEmployeeByEmployeeNo(String employeeNo);

}
