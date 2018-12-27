package com.tsingshan.infomana.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.infomana.domain.Dimission;
import java.util.List;

/**
 * 离职 服务层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface IDimissionService 
{
	/**
     * 查询离职信息
     * 
     * @param id 离职ID
     * @return 离职信息
     */
	Dimission selectDimissionById(long id);
	
	/**
     * 查询离职列表
     * 
     * @param dimission 离职信息
     * @return 离职集合
     */
	List<Dimission> selectDimissionList(Dimission dimission);
	
	/**
     * 新增离职
     * 
     * @param dimission 离职信息
     * @return 结果
     */
	AjaxResult insertDimission(Dimission dimission);
	
	/**
     * 修改离职
     * 
     * @param dimission 离职信息
     * @return 结果
     */
	AjaxResult updateDimission(Dimission dimission);
		
	/**
     * 删除离职信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	AjaxResult deleteDimissionByIds(String ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Dimission> selectExport(String ids);
	
}
