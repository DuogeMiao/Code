package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.vo.PassportInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 护照 数据层
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
public interface PassportInfoVoMapper
{
	/**
     * 查询护照信息
     * 
     * @param id 护照ID
     * @return 护照信息
     */
	PassportInfoVo selectPassportInfoVoById(Long id);
	
	/**
     * 查询护照列表
     * 
     * @param passportInfoVo 护照信息
     * @return 护照集合
     */
	List<PassportInfoVo> selectPassportInfoVoList(PassportInfoVo passportInfoVo);

    List<PassportInfoVo> exportPassportInfoVo(@Param("ids") Long[] ids);
}