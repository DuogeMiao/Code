package com.tsingshan.infomana.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.infomana.domain.Grade;
import java.util.List;

/**
 * 级档 服务层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface IGradeService 
{
	/**
     * 查询级档信息
     * 
     * @param id 级档ID
     * @return 级档信息
     */
	Grade selectGradeById(int id);
	
	/**
     * 查询级档列表
     * 
     * @param grade 级档信息
     * @return 级档集合
     */
	List<Grade> selectGradeList(Grade grade);
	
	/**
     * 新增级档
     * 
     * @param grade 级档信息
     * @return 结果
     */
	AjaxResult insertGrade(Grade grade);
	
	/**
     * 修改级档
     * 
     * @param grade 级档信息
     * @return 结果
     */
	AjaxResult updateGrade(Grade grade);
		
	/**
     * 删除级档信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	AjaxResult deleteGradeByIds(String ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Grade> selectExport(String ids);
	
}
