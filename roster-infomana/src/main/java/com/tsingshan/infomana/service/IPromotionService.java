package com.tsingshan.infomana.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.infomana.domain.Promotion;
import java.util.List;

/**
 * 晋升 服务层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface IPromotionService 
{
	/**
     * 查询晋升信息
     * 
     * @param id 晋升ID
     * @return 晋升信息
     */
	Promotion selectPromotionById(long id);
	
	/**
     * 查询晋升列表
     * 
     * @param promotion 晋升信息
     * @return 晋升集合
     */
	List<Promotion> selectPromotionList(Promotion promotion);
	
	/**
     * 新增晋升
     * 
     * @param promotion 晋升信息
     * @return 结果
     */
	AjaxResult insertPromotion(Promotion promotion,long postId, long jobId);
	
	/**
     * 修改晋升
     * 
     * @param promotion 晋升信息
     * @return 结果
     */
	AjaxResult updatePromotion(Promotion promotion);
		
	/**
     * 删除晋升信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	AjaxResult deletePromotionByIds(String ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Promotion> selectExport(String ids);
	
}
