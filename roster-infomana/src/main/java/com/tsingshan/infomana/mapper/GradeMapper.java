package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 级档 数据层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface GradeMapper 
{
	/**
     * 查询级档信息
     * 
     * @param id 级档ID
     * @return 级档信息
     */
	public Grade selectGradeById(Integer id);
	
	/**
     * 查询级档列表
     * 
     * @param grade 级档信息
     * @return 级档集合
     */
	public List<Grade> selectGradeList(Grade grade);
	
	/**
     * 新增级档
     * 
     * @param grade 级档信息
     * @return 结果
     */
	public int insertGrade(Grade grade);
	
	/**
     * 修改级档
     * 
     * @param grade 级档信息
     * @return 结果
     */
	public int updateGrade(Grade grade);
	
	/**
     * 删除级档
     * 
     * @param id 级档ID
     * @return 结果
     */
	public int deleteGradeById(Integer id);
	
	/**
     * 批量删除级档
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteGradeByIds(Integer[] ids);


	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Grade> selectExport(@Param("ids") Integer[] ids);
	
}