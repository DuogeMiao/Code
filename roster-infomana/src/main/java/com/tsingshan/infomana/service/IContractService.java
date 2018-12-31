package com.tsingshan.infomana.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.infomana.domain.Contract;
import com.tsingshan.infomana.domain.vo.ContractVo;

import java.util.List;

/**
 * 合同 服务层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface IContractService 
{
	/**
     * 查询合同信息
     * 
     * @param contractId 合同ID
     * @return 合同信息
     */
	Contract selectContractById(long contractId);
	
	/**
     * 查询合同列表
     * 
     * @param contract 合同信息
     * @return 合同集合
     */
	List<Contract> selectContractList(Contract contract);
	
	/**
     * 新增合同
     * 
     * @param contract 合同信息
     * @return 结果
     */
	AjaxResult insertContract(Contract contract);

    /**
     *
     * @param contract
     * @return
     */
    AjaxResult reSignContract(Contract contract);

	/**
     * 修改合同
     * 
     * @param contract 合同信息
     * @return 结果
     */
	AjaxResult updateContract(Contract contract);
		
	/**
     * 删除合同信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	AjaxResult deleteContractByIds(String ids);

	/**
	 * 根据id查询显示任意列表 导出
	 * @param ids
	 * @return
	 */
	List<Contract> selectExport(String ids);

	/**
	 * 根据员工id数组查询合同
	 * @param ids
	 * @return
	 */
	List<Contract> selectByEmployeeIds(String ids);



}
