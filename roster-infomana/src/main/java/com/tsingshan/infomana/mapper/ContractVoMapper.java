package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.vo.ContractVo;
import java.util.List;

/**
 * 合同 数据层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface ContractVoMapper
{

	/**
     * 查询合同列表
     * 
     * @param contractVo 合同信息
     * @return 合同集合
     */
	List<ContractVo> selectContractVoList(ContractVo contractVo);


    ContractVo selectContractVoById(Long contractId);
}