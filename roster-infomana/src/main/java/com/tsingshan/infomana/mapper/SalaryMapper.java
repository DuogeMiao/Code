package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.Salary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 调薪 数据层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface SalaryMapper 
{
	/**
     * 查询调薪信息
     * 
     * @param id 调薪ID
     * @return 调薪信息
     */
	Salary selectSalaryById(Long id);

	Salary selectSalaryByEmployeeNoState(@Param("employeeNo") String employeeNo, @Param("state") String state);
	
	/**
     * 查询调薪列表
     * 
     * @param salary 调薪信息
     * @return 调薪集合
     */
	List<Salary> selectSalaryList(Salary salary);
	
	/**
     * 新增调薪
     * 
     * @param salary 调薪信息
     * @return 结果
     */
	int insertSalary(Salary salary);
	
	/**
     * 修改调薪
     * 
     * @param salary 调薪信息
     * @return 结果
     */
	int updateSalary(Salary salary);
	
	/**
     * 删除调薪
     * 
     * @param id 调薪ID
     * @return 结果
     */
	int deleteSalaryById(Long id);
	
	/**
     * 批量删除调薪
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteSalaryByIds(Long[] ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Salary> selectExport(@Param("ids") Long[] ids);
	
}