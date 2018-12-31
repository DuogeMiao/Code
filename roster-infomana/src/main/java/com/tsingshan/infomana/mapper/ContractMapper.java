package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 合同 数据层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface ContractMapper 
{
	/**
     * 查询合同信息
     * 
     * @param contractId 合同ID
     * @return 合同信息
     */
	Contract selectContractById(Long contractId);
	
	/**
     * 查询合同列表
     * 
     * @param contract 合同信息
     * @return 合同集合
     */
	List<Contract> selectContractList(Contract contract);


    /**
     * 根据合同号查询
     * @param contractNo
     * @return
     */
	List<Contract> selectContractListByContractNo(String contractNo);

	/**
     * 新增合同
     * 
     * @param contract 合同信息
     * @return 结果
     */
	int insertContract(Contract contract);
	
	/**
     * 修改合同
     * 
     * @param contract 合同信息
     * @return 结果
     */
	int updateContract(Contract contract);
	
	/**
     * 删除合同
     * 
     * @param contractId 合同ID
     * @return 结果
     */
	int deleteContractById(Long contractId);
	
	/**
     * 批量删除合同
     * 
     * @param contractIds 需要删除的数据ID
     * @return 结果
     */
	int deleteContractByIds(Long[] contractIds);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Contract> selectExport(@Param("ids") Long[] ids);

	/**
	 * 根据员工id数组查询合同
	 * @param ids
	 * @return
	 */
	List<Contract> selectByEmployeeIds(@Param("ids") Long[] ids);


}