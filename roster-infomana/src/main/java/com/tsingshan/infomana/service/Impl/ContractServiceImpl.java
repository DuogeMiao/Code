package com.tsingshan.infomana.service.Impl;

import java.util.Date;
import java.util.List;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import com.tsingshan.common.utils.DateUtils;
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.domain.Transaction;
import com.tsingshan.infomana.domain.vo.ContractVo;
import com.tsingshan.infomana.domain.vo.EmployeeVo;
import com.tsingshan.infomana.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.domain.Contract;
import com.tsingshan.infomana.service.IContractService;


/**
 * 合同 服务层实现
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Service
public class ContractServiceImpl implements IContractService 
{
    private static final Logger log = LoggerFactory.getLogger(ContractServiceImpl.class);

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
    private EmployeeVoMapper employeeVoMapper;

	@Autowired
    private EmployeeMapper employeeMapper;

	/**
     * 查询合同信息
     * 
     * @param contractId 合同ID
     * @return 合同信息
     */
    @Override
	public Contract selectContractById(long contractId)
	{
	    return contractMapper.selectContractById(contractId);
	}

	/*@Override
	public ContractVo selectContractVoById(long contractId) {
            return contractVoMapper.selectContractVoById(contractId);
	}*/

	/**
     * 查询合同列表
     * 
     * @param contract 合同信息
     * @return 合同集合
     */
	@Override
	public List<Contract> selectContractList(Contract contract)
	{
	    return contractMapper.selectContractList(contract);
	}

	/**
     * 新增合同
     * 
     * @param contract 合同信息
     * @return 结果
     */
	@Override
	public AjaxResult insertContract(Contract contract)
	{
		try {
		    //设置合同状态 0 未过期  1 过期
		    contract.setState("0");
		    // 根据合同年限来设置合同过期时间
            Date date = DateUtils.dateYear(contract.getYearLimit(), contract.getSignDate());
            contract.setExpireDate(date);
            contractMapper.insertContract(contract);
			Employee employee = employeeMapper.selectEmployeeById(contract.getEmployeeId());
			//更新员工的合同状态  0 已签订  1 未签订
			employee.setContractStatus("0");
			employeeMapper.updateEmployee(employee);
            return AjaxResult.success();
        } catch (Exception e) {
		    log.error("新增合同异常{}", e.getMessage());
		    return AjaxResult.error("新增合同异常!");
        }

	}

	@Override
	public AjaxResult reSignContract(Contract contract) {
        try {
            //通过合同id获取旧合同
            Contract lastContract = contractMapper.selectContractById(contract.getContractId());
            List<Contract> contractList = contractMapper.selectContractListByContractNo(contract.getContractNo());
            if (contractList.size() > 0) {
                return AjaxResult.error("已有该合同号");
            }
            //把旧合同设置过期
            lastContract.setState("1");
            updateContract(lastContract);
            //插入新合同
            contract.setContractId(null);
            // 根据合同年限来设置合同过期时间
            Date date = DateUtils.dateYear(contract.getYearLimit(), contract.getSignDate());
            contract.setExpireDate(date);
            contractMapper.insertContract(contract);
            return AjaxResult.success();
        } catch (Exception e) {
            log.error("重签合同异常{}", e.getMessage());
            return AjaxResult.error("重签合同异常");
        }
	}

	/**
     * 修改合同
     * 
     * @param contract 合同信息
     * @return 结果
     */
	@Override
	public AjaxResult updateContract(Contract contract)
	{
	    try {
            // 根据合同年限来设置合同过期时间
            Date date = DateUtils.dateYear(contract.getYearLimit(), contract.getSignDate());
            contract.setExpireDate(date);
            contractMapper.updateContract(contract);
            return AjaxResult.success();
        } catch (Exception e) {
	        log.error("更新合同异常{}", e.getMessage());
	        return AjaxResult.error("修改异常");
        }
	}

	/**
     * 删除合同对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deleteContractByIds(String ids)
	{
	    try {
            contractMapper.deleteContractByIds(Convert.toLongArray(ids));
            return AjaxResult.success();
        } catch (Exception e) {
	        log.error("根据Id删除合同异常{}", e.getMessage());
	        return AjaxResult.error("删除异常");
        }

	}

	@Override
	public List<Contract> selectExport(String ids) {
		return contractMapper.selectExport(Convert.toLongArray(ids));
	}

	@Override
	public List<Contract> selectByEmployeeIds(String ids) {
		return contractMapper.selectByEmployeeIds(Convert.toLongArray(ids));
	}
}
