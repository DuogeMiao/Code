package com.tsingshan.infomana.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.infomana.domain.Transaction;
import java.util.List;

/**
 * 异动 服务层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface ITransactionService 
{
	/**
     * 查询异动信息
     * 
     * @param id 异动ID
     * @return 异动信息
     */
	Transaction selectTransactionById(long id);
	
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
	AjaxResult insertTransaction(Transaction transaction);
	
	/**
     * 修改异动
     * 
     * @param transaction 异动信息
     * @return 结果
     */
	AjaxResult updateTransaction(Transaction transaction);
		
	/**
     * 删除异动信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	AjaxResult deleteTransactionByIds(String ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Transaction> selectExport(String ids);
	
}
