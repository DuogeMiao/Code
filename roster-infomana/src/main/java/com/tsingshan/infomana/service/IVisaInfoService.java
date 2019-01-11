package com.tsingshan.infomana.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.infomana.domain.VisaInfo;
import java.util.List;

/**
 * 签证 服务层
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
public interface IVisaInfoService 
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

//	List<VisaInfoVo> selectVisaInfoVoList(VisaInfoVo visaInfoVo);

	/**
     * 新增签证
     * 
     * @param visaInfo 签证信息
     * @return 结果
     */
	AjaxResult insertVisaInfo(VisaInfo visaInfo);
	
	/**
     * 修改签证
     * 
     * @param visaInfo 签证信息
     * @return 结果
     */
	int updateVisaInfo(VisaInfo visaInfo);
		
	/**
     * 删除签证信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteVisaInfoByIds(String ids);


    /**
     * 导出
     * @param ids
     * @return
     */
    List<VisaInfo> exportVisaInfo(String ids);
}
