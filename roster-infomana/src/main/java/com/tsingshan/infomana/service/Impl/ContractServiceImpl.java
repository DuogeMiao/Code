package com.tsingshan.infomana.service.Impl;

import java.util.Date;
import java.util.List;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
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
	private ContractVoMapper contractVoMapper;

	@Autowired
	private TransactionMapper transactionMapper;

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

	@Override
	public ContractVo selectContractVoById(long contractId) {
            return contractVoMapper.selectContractVoById(contractId);
	}

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

	@Override
	public List<ContractVo> selectContractVoList(ContractVo contractVo) {
		return contractVoMapper.selectContractVoList(contractVo);
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
            ContractVo lastContractVo = contractVoMapper.selectContractVoById(contract.getContractId());
            Contract lastContract = contractMapper.selectContractById(contract.getContractId());
            //判断当前公司是否与原公司相等，不相等则签订新合同，并增加异动信息，以及更新员工信息
            if (null != contract.getCompanyId()) {
                if (lastContract.getContractNo().equals(contract.getContractNo())) {
                    return AjaxResult.error("请重新填写合同号！");
                } else if (lastContract.getCompanyId() == contract.getCompanyId()) {
                    return AjaxResult.error("重签合同时：原公司跟现在公司必须不同");
                } else {
                	//把旧合同设置过期
                    lastContract.setStatus("1");
                    updateContract(lastContract);
                    //插入新合同
                    contract.setContractId(null);
                    contractMapper.insertContract(contract);
                    //更新员工表
                    updateEmployee(contract);
                    Thread.sleep(1000);
                    //跨公司 增加异动信息
                    insertTrans(lastContractVo,contract.getSignDate());
                    return AjaxResult.success();
                }
            }
            return AjaxResult.error("数据异常");
        } catch (Exception e) {
            log.error("重签合同异常{}", e.getMessage());
            return AjaxResult.error("重签合同异常");
        }
	}

	private void updateEmployee(Contract contract) {
        Employee employee = employeeMapper.selectEmployeeById(contract.getEmployeeId());
        employee.setEmployeeNo(contract.getEmployeeNo());
        employee.setIdentityCard(contract.getIdentityCard());
        employee.setEmployeeName(contract.getEmployeeName());
        employee.setCompanyId(contract.getCompanyId());
        employee.setDeptId(contract.getDeptId());
        employee.setPostId(contract.getPostId());
        employee.setJobId(contract.getJobId());
        employee.setBankCard(contract.getBankCard());
        employee.setAccountBank(contract.getAccountBank());
        employeeMapper.updateEmployee(employee);
    }

	private void insertTrans(ContractVo contractVo, Date date) {
        EmployeeVo employeeVo = employeeVoMapper.selectEmployeeVoById(contractVo.getEmployeeId());
		Transaction transaction = new Transaction();
		transaction.setEmployeeId(contractVo.getEmployeeId());
		transaction.setEmployeeNo(contractVo.getEmployeeNo());
		transaction.setEmployeeName(contractVo.getEmployeeName());
		transaction.setIdentityCard(contractVo.getIdentityCard());
		transaction.setTransOutCompany(contractVo.getCompanyCode());
		transaction.setTransOutDept(contractVo.getDeptName());
		transaction.setTransOutPost(contractVo.getPostName());
		transaction.setSpanCompany("是");
		transaction.setTransInCompany(employeeVo.getCompanyCode());
		transaction.setTransInDept(employeeVo.getDeptName());
		transaction.setTransInPost(employeeVo.getPostName());
		transaction.setEffectiveDate(date);
		transactionMapper.insertTransaction(transaction);
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
