package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.PassportInfo;
import com.tsingshan.infomana.domain.vo.PassportInfoVo;

import java.util.List;	

/**
 * 护照 数据层
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
public interface PassportInfoMapper 
{
	/**
     * 查询护照信息
     * 
     * @param id 护照ID
     * @return 护照信息
     */
	PassportInfo selectPassportInfoById(Long id);
	
	/**
     * 查询护照列表
     * 
     * @param passportInfo 护照信息
     * @return 护照集合
     */
	List<PassportInfo> selectPassportInfoList(PassportInfo passportInfo);
	
	/**
     * 新增护照
     * 
     * @param passportInfo 护照信息
     * @return 结果
     */
	int insertPassportInfo(PassportInfo passportInfo);
	
	/**
     * 修改护照
     * 
     * @param passportInfo 护照信息
     * @return 结果
     */
	int updatePassportInfo(PassportInfo passportInfo);
	
	/**
     * 删除护照
     * 
     * @param id 护照ID
     * @return 结果
     */
	int deletePassportInfoById(Long id);
	
	/**
     * 批量删除护照
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deletePassportInfoByIds(Long[] ids);

    /**
     * 校验护照是否唯一
     * @param passportNo
     * @return
     */
    PassportInfo checkPassportNoUnique(String passportNo);


    List<PassportInfo> selectPassportInfoAllByState(String state);
}