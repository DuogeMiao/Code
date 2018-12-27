package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.Dimission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 离职 数据层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface DimissionMapper 
{
	/**
     * 查询离职信息
     * 
     * @param id 离职ID
     * @return 离职信息
     */
	Dimission selectDimissionById(Long id);
	
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
	int insertDimission(Dimission dimission);
	
	/**
     * 修改离职
     * 
     * @param dimission 离职信息
     * @return 结果
     */
	int updateDimission(Dimission dimission);
	
	/**
     * 删除离职
     * 
     * @param id 离职ID
     * @return 结果
     */
	int deleteDimissionById(Long id);
	
	/**
     * 批量删除离职
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteDimissionByIds(Long[] ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Dimission> selectExport(@Param("ids") Long[] ids);
	
}