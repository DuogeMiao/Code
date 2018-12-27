package com.tsingshan.infomana.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.infomana.domain.Salary;
import java.util.List;

/**
 * 调薪 服务层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface ISalaryService 
{
	/**
     * 查询调薪信息
     * 
     * @param id 调薪ID
     * @return 调薪信息
     */
	Salary selectSalaryById(long id);
	
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
	AjaxResult insertSalary(Salary salary);
	
	/**
     * 修改调薪
     * 
     * @param salary 调薪信息
     * @return 结果
     */
	AjaxResult updateSalary(Salary salary);
		
	/**
     * 删除调薪信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	AjaxResult deleteSalaryByIds(String ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Salary> selectExport(String ids);
	
}
