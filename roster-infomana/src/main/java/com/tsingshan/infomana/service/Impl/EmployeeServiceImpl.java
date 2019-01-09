package com.tsingshan.infomana.service.Impl;

import java.util.List;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.EmployeeMapper;
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.service.IEmployeeService;


/**
 * 员工 服务层实现
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService 
{
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeMapper employeeMapper;

	/**
     * 查询员工信息
     * 
     * @param employeeId 员工ID
     * @return 员工信息
     */
    @Override
	public Employee selectEmployeeById(long employeeId)
	{
	    return employeeMapper.selectEmployeeById(employeeId);
	}

	/**
     * 查询员工列表
     * 
     * @param employee 员工信息
     * @return 员工集合
     */
	@Override
	public List<Employee> selectEmployeeList(Employee employee)
	{
	    return employeeMapper.selectEmployeeList(employee);
	}


	@Override
	public List<Employee> selectEmployeeListByState(String state) {
		return employeeMapper.selectEmployeeListByStatus(state);
	}

    @Override
    public List<Employee> selectEmployeeListByStateAndContractStatus(String state, String contractStatus) {
        return employeeMapper.selectEmployeeListByStateAndContractStatus(state, contractStatus);
    }


    /**
     * 新增员工
     * 
     * @param employee 员工信息
     * @return 结果
     */
	@Override
	public AjaxResult insertEmployee(Employee employee)
	{
		try {
		    //设置在职状态 0 在职 1 离职
		    employee.setState("0");
		    //设置签订合同状态 0 已签订 1 未签订
		    employee.setContractStatus("1");
			employeeMapper.insertEmployee(employee);
			return AjaxResult.success();
		} catch (Exception e) {
            logger.error("添加员工信息异常{}", e.getMessage());
            return AjaxResult.error("增加异常");
		}
	}

    @Override
    public AjaxResult batchInsertEmployee(List<Employee> employeeList) {
        try {
            employeeMapper.batchInsertEmployee(employeeList);
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("批量插入异常{}", e.getMessage());
            return AjaxResult.error("批量增加异常");
        }
    }

    /**
     * 修改员工
     * 
     * @param employee 员工信息
     * @return 结果
     */
	@Override
	public AjaxResult updateEmployee(Employee employee)
	{
	    try {
            employeeMapper.updateEmployee(employee);
            return AjaxResult.success();
        } catch (Exception e) {
	        logger.error("更新员工信息异常{}", e.getMessage());
            return AjaxResult.error("更新异常");
        }

	}

	/**
     * 删除员工对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deleteEmployeeByIds(String ids)
	{
	    try {
            employeeMapper.deleteEmployeeByIds(Convert.toLongArray(ids));
            return AjaxResult.success();
        } catch (Exception e) {
	        logger.error("根据ID删除员工信息异常{}", e.getMessage());
	        return AjaxResult.error("删除异常");
        }
	}

	@Override
	public List<Employee> selectExport(String ids) {
		return employeeMapper.selectExport(Convert.toLongArray(ids));
	}

    @Override
    public Employee selectEmployeeByEmployeeNo(String employeeNo) {
        return employeeMapper.selectEmployeeByEmployeeNo(employeeNo);
    }

}
