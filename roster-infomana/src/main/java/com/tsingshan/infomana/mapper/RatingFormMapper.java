package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.RatingForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工考核 数据层
 * 
 * @author tsingshan
 * @date 2018-12-28
 */
public interface RatingFormMapper 
{
	/**
     * 查询员工考核信息
     * 
     * @param id 员工考核ID
     * @return 员工考核信息
     */
	RatingForm selectRatingFormById(Long id);
	
	/**
     * 查询员工考核列表
     * 
     * @param ratingForm 员工考核信息
     * @return 员工考核集合
     */
	List<RatingForm> selectRatingFormList(RatingForm ratingForm);
	
	/**
     * 新增员工考核
     * 
     * @param ratingForm 员工考核信息
     * @return 结果
     */
	int insertRatingForm(RatingForm ratingForm);
	
	/**
     * 修改员工考核
     * 
     * @param ratingForm 员工考核信息
     * @return 结果
     */
	int updateRatingForm(RatingForm ratingForm);
	
	/**
     * 删除员工考核
     * 
     * @param id 员工考核ID
     * @return 结果
     */
	int deleteRatingFormById(Long id);
	
	/**
     * 批量删除员工考核
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteRatingFormByIds(Long[] ids);

    /**
     * 根据id查询显示任意列表
     * @param ids
     * @return
     */
    List<RatingForm> selectRatingForm(@Param("ids") Long[] ids);
	
}