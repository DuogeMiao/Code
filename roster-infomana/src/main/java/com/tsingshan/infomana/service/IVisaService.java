package com.tsingshan.infomana.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.infomana.domain.Visa;
import java.util.List;

/**
 * 护照签证 服务层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface IVisaService 
{
	/**
     * 查询护照签证信息
     * 
     * @param id 护照签证ID
     * @return 护照签证信息
     */
	Visa selectVisaById(long id);
	
	/**
     * 查询护照签证列表
     * 
     * @param visa 护照签证信息
     * @return 护照签证集合
     */
	List<Visa> selectVisaList(Visa visa);
	
	/**
     * 新增护照签证
     * 
     * @param visa 护照签证信息
     * @return 结果
     */
	AjaxResult insertVisa(Visa visa);
	
	/**
     * 修改护照签证
     * 
     * @param visa 护照签证信息
     * @return 结果
     */
	AjaxResult updateVisa(Visa visa);
		
	/**
     * 删除护照签证信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	AjaxResult deleteVisaByIds(String ids);


	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Visa> selectExport(String ids);
	
}
