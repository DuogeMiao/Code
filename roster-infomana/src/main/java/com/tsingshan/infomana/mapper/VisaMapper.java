package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.Visa;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 护照签证 数据层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface VisaMapper 
{
	/**
     * 查询护照签证信息
     * 
     * @param id 护照签证ID
     * @return 护照签证信息
     */
	Visa selectVisaById(Long id);

	/**
	 * 查询护照签证信息
	 * @param employeeNo 工号
	 * @param state	状态
	 * @return 护照签证信息
	 */
	Visa selectVisaByEmployeeNoState(@Param("employeeNo") String employeeNo,@Param("state") String state);
	
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
	int insertVisa(Visa visa);
	
	/**
     * 修改护照签证
     * 
     * @param visa 护照签证信息
     * @return 结果
     */
	int updateVisa(Visa visa);
	
	/**
     * 删除护照签证
     * 
     * @param id 护照签证ID
     * @return 结果
     */
	int deleteVisaById(Long id);
	
	/**
     * 批量删除护照签证
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteVisaByIds(Long[] ids);

	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<Visa> selectExport( @Param("ids") Long[] ids);
	
}