package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.vo.VisaInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 签证 数据层
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
public interface VisaInfoVoMapper
{
	/**
     * 查询签证信息
     * 
     * @param id 签证ID
     * @return 签证信息
     */
	VisaInfoVo selectVisaInfoVoById(Long id);
	
	/**
     * 查询签证列表
     * 
     * @param visaInfoVo 签证信息
     * @return 签证集合
     */
	List<VisaInfoVo> selectVisaInfoVoList(VisaInfoVo visaInfoVo);


    List<VisaInfoVo> exportVisaInfoVo(@Param("ids") Long[] ids);
}