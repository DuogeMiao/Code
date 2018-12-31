package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 异动 数据层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface TransactionMapper 
{
	/**
     * 查询异动信息
     * 
     * @param id 异动ID
     * @return 异动信息
     */
	Transaction selectTransactionById(Long id);
	
	/**
     * 查询异动列表
     * 
     * @param transaction 异动信息
     * @return 异动集合
     */
	List<Transaction> selectTransactionList(Transaction transaction);
	
	/**
     * 新增异动
     * 
     * @param transaction 异动信息
     * @return 结果
     */
	int insertTransaction(Transaction transaction);
	
	/**
     * 修改异动
     * 
     * @param transaction 异动信息
     * @return 结果
     */
	int updateTransaction(Transaction transaction);
	
	/**
     * 删除异动
     * 
     * @param id 异动ID
     * @return 结果
     */
	int deleteTransactionById(Long id);
	
	/**
     * 批量删除异动
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteTransactionByIds(String[] ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Transaction> selectExport(@Param("ids") Long[] ids);
	
}