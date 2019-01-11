package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.VisaInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 签证 数据层
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
public interface VisaInfoMapper 
{
	/**
     * 查询签证信息
     * 
     * @param id 签证ID
     * @return 签证信息
     */
	VisaInfo selectVisaInfoById(Long id);
	
	/**
     * 查询签证列表
     * 
     * @param visaInfo 签证信息
     * @return 签证集合
     */
	List<VisaInfo> selectVisaInfoList(VisaInfo visaInfo);
	
	/**
     * 新增签证
     * 
     * @param visaInfo 签证信息
     * @return 结果
     */
	int insertVisaInfo(VisaInfo visaInfo);
	
	/**
     * 修改签证
     * 
     * @param visaInfo 签证信息
     * @return 结果
     */
	int updateVisaInfo(VisaInfo visaInfo);
	
	/**
     * 删除签证
     * 
     * @param id 签证ID
     * @return 结果
     */
	int deleteVisaInfoById(Long id);
	
	/**
     * 批量删除签证
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteVisaInfoByIds(Long[] ids);

    List<VisaInfo> exportVisaInfo(@Param("ids") Long[] ids);
}