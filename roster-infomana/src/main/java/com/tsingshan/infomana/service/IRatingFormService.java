package com.tsingshan.infomana.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.infomana.domain.RatingForm;
import java.util.List;

/**
 * 员工考核 服务层
 * 
 * @author tsingshan
 * @date 2018-12-28
 */
public interface IRatingFormService 
{
	/**
     * 查询员工考核信息
     * 
     * @param id 员工考核ID
     * @return 员工考核信息
     */
	RatingForm selectRatingFormById(long id);
	
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
	AjaxResult insertRatingForm(RatingForm ratingForm);
	
	/**
     * 修改员工考核
     * 
     * @param ratingForm 员工考核信息
     * @return 结果
     */
    AjaxResult updateRatingForm(RatingForm ratingForm);
		
	/**
     * 删除员工考核信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    AjaxResult deleteRatingFormByIds(String ids);

    /**
     * 根据id 导出
     * @param ids
     * @return
     */
    List<RatingForm> exportRatingForm(String ids);

	
}
