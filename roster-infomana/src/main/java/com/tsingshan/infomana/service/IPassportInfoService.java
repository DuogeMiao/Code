package com.tsingshan.infomana.service;

import com.tsingshan.infomana.domain.PassportInfo;
import java.util.List;

/**
 * 护照 服务层
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
public interface IPassportInfoService 
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

	List<PassportInfo> selectPassportInfoAllByState(String state);

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
     * 删除护照信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deletePassportInfoByIds(String ids);

    /**
     * 校验护照号是否唯一
     * @param passportNo
     * @return
     */
    String checkPassportNoUnique(String passportNo);

    /**
     * 导出
     * @param ids
     * @return
     */
    List<PassportInfo> exportPassportInfo(String ids);
}
