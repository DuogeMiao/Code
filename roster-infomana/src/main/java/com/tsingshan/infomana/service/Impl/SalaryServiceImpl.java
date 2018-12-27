package com.tsingshan.infomana.service.Impl;

import java.util.List;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.SalaryMapper;
import com.tsingshan.infomana.domain.Salary;
import com.tsingshan.infomana.service.ISalaryService;

/**
 * 调薪 服务层实现
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Service
public class SalaryServiceImpl implements ISalaryService 
{
	private static final Logger logger = LoggerFactory.getLogger(SalaryServiceImpl.class);

	@Autowired
	private SalaryMapper salaryMapper;

	/**
     * 查询调薪信息
     * 
     * @param id 调薪ID
     * @return 调薪信息
     */
    @Override
	public Salary selectSalaryById(long id)
	{
	    return salaryMapper.selectSalaryById(id);
	}
	
	/**
     * 查询调薪列表
     * 
     * @param salary 调薪信息
     * @return 调薪集合
     */
	@Override
	public List<Salary> selectSalaryList(Salary salary)
	{
	    return salaryMapper.selectSalaryList(salary);
	}
	
    /**
     * 新增调薪
     * 
     * @param salary 调薪信息
     * @return 结果
     */
	@Override
	public AjaxResult insertSalary(Salary salary)
	{

		try {
			Salary salaryStatus = salaryMapper.selectSalaryByEmployeeNoStatus(salary.getEmployeeNo(), "0");
			salaryStatus.setStatus("1");
			salaryMapper.updateSalary(salaryStatus);
			salary.setStatus("0");
			salaryMapper.insertSalary(salary);
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("添加调薪记录异常{}", e.getMessage());
			return AjaxResult.error("添加异常");
		}
	}
	
	/**
     * 修改调薪
     * 
     * @param salary 调薪信息
     * @return 结果
     */
	@Override
	public AjaxResult updateSalary(Salary salary)
	{
		try {
			salaryMapper.updateSalary(salary);
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("更新调薪信息异常{}", e.getMessage());
			return AjaxResult.error("修改异常");
		}
	}

	/**
     * 删除调薪对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deleteSalaryByIds(String ids)
	{

		try {
			salaryMapper.deleteSalaryByIds(Convert.toLongArray(ids));
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("根据ID删除调薪信息异常{}", e.getMessage());
			return AjaxResult.error("删除异常");
		}
	}

	@Override
	public List<Salary> selectExport(String ids) {
		return salaryMapper.selectExport(Convert.toLongArray(ids));
	}

}
