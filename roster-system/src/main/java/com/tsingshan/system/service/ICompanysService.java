package com.tsingshan.system.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.system.domain.Companys;
import java.util.List;

/**
 * 公司 服务层
 * 
 * @author tsingshan
 * @date 2018-12-20
 */
public interface ICompanysService 
{
	/**
     * 查询公司信息
     * 
     * @param companyId 公司ID
     * @return 公司信息
     */
	Companys selectCompanysById(long companyId);
	
	/**
     * 查询公司列表
     * 
     * @param companys 公司信息
     * @return 公司集合
     */
	List<Companys> selectCompanysList(Companys companys);
	
	/**
     * 新增公司
     * 
     * @param companys 公司信息
     * @return 结果
     */
	AjaxResult insertCompanys(Companys companys);
	
	/**
     * 修改公司
     * 
     * @param companys 公司信息
     * @return 结果
     */
	AjaxResult updateCompanys(Companys companys);
		
	/**
     * 删除公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	AjaxResult deleteCompanysByIds(String ids);

	List<Companys> selectCompanyAll();
	
}
