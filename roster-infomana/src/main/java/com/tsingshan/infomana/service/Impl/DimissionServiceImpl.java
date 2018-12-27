package com.tsingshan.infomana.service.Impl;

import java.util.List;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import com.tsingshan.infomana.domain.Contract;
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.mapper.ContractMapper;
import com.tsingshan.infomana.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.DimissionMapper;
import com.tsingshan.infomana.domain.Dimission;
import com.tsingshan.infomana.service.IDimissionService;

/**
 * 离职 服务层实现
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Service
public class DimissionServiceImpl implements IDimissionService 
{
	private static final Logger logger = LoggerFactory.getLogger(DimissionServiceImpl.class);

	@Autowired
	private DimissionMapper dimissionMapper;

	@Autowired
    private EmployeeMapper employeeMapper;

	@Autowired
    private ContractMapper contractMapper;

	/**
     * 查询离职信息
     * 
     * @param id 离职ID
     * @return 离职信息
     */
    @Override
	public Dimission selectDimissionById(long id)
	{
	    return dimissionMapper.selectDimissionById(id);
	}
	
	/**
     * 查询离职列表
     * 
     * @param dimission 离职信息
     * @return 离职集合
     */
	@Override
	public List<Dimission> selectDimissionList(Dimission dimission)
	{
	    return dimissionMapper.selectDimissionList(dimission);
	}
	
    /**
     * 新增离职
     * 
     * @param dimission 离职信息
     * @return 结果
     */
	@Override
	public AjaxResult insertDimission(Dimission dimission)
	{
		try {
		    if (dimission.getEmployeeNo() == null || dimission.getEmployeeNo().equals("")) {
                return AjaxResult.error("工号不能为空");
            }
			dimissionMapper.insertDimission(dimission);
		    //更新员工信息表
            updateEmployee(dimission.getEmployeeId());
            updateContract(dimission.getEmployeeId());
            return AjaxResult.success();
		} catch (Exception e) {
			logger.error("添加离职信息异常{}", e.getMessage());
			return AjaxResult.error("添加异常");
		}
	}

	private void updateEmployee(Long employeeId) {
        Employee employee = employeeMapper.selectEmployeeById(employeeId);
        employee.setStatus("1");
        employeeMapper.updateEmployee(employee);
    }

    private void updateContract(Long employeeId) {
	    Contract contract = new Contract();
	    contract.setEmployeeId(employeeId);
        List<Contract> contractList = contractMapper.selectContractList(contract);
        for (Contract con : contractList) {
            if (con.getStatus().equals("0")) {
                con.setStatus("1");
                contractMapper.updateContract(con);
            }
        }
    }
	
	/**
     * 修改离职
     * 
     * @param dimission 离职信息
     * @return 结果
     */
	@Override
	public AjaxResult updateDimission(Dimission dimission)
	{
		try {
			dimissionMapper.updateDimission(dimission);
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("更新离职信息异常{}", e.getMessage());
			return AjaxResult.error("修改异常");
		}
	}

	/**
     * 删除离职对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deleteDimissionByIds(String ids)
	{
		try {
			dimissionMapper.deleteDimissionByIds(Convert.toLongArray(ids));
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("根据ID删除离职信息异常{}", e.getMessage());
			return AjaxResult.error("删除异常");
		}
	}

	@Override
	public List<Dimission> selectExport(String ids) {
		return dimissionMapper.selectExport(Convert.toLongArray(ids));
	}

}
