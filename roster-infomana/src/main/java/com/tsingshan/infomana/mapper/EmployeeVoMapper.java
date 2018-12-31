package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.vo.EmployeeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工 数据层
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
public interface EmployeeVoMapper
{
	/**
     * 查询员工信息
     *
     * @param employeeId 员工ID
     * @return 员工信息
     */
	EmployeeVo selectEmployeeVoById(Long employeeId);

	EmployeeVo selectEmployeeVoByEmployeeNo(String employeeNo);

	/**
     * 查询员工列表
     *
     * @param employee 员工信息
     * @return 员工集合
     */
	List<EmployeeVo> selectEmployeeVoList(EmployeeVo employee);

	List<EmployeeVo> selectEmployeeVoListByState(@Param("state") String state, @Param("contractStatus") String contractStatus);


	/**
	 * 根据id查询显示任意列表
	 * @param ids
	 * @return
	 */
	List<EmployeeVo> selectExport(@Param("ids") Long[] ids);

}