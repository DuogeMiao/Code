package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.Promotion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 晋升 数据层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface PromotionMapper 
{
	/**
     * 查询晋升信息
     * 
     * @param id 晋升ID
     * @return 晋升信息
     */
	Promotion selectPromotionById(Long id);
	
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
	int insertPromotion(Promotion promotion);
	
	/**
     * 修改晋升
     * 
     * @param promotion 晋升信息
     * @return 结果
     */
	int updatePromotion(Promotion promotion);
	
	/**
     * 删除晋升
     * 
     * @param id 晋升ID
     * @return 结果
     */
	int deletePromotionById(Long id);
	
	/**
     * 批量删除晋升
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deletePromotionByIds(Long[] ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Promotion> selectExport(@Param("ids") Long[] ids);
	
}