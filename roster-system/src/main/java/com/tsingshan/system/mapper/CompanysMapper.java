package com.tsingshan.system.mapper;

import com.tsingshan.system.domain.Companys;
import java.util.List;

/**
 * 公司 数据层
 * 
 * @author tsingshan
 * @date 2018-12-20
 */
public interface CompanysMapper 
{
	/**
     * 查询公司信息
     * 
     * @param companyId 公司ID
     * @return 公司信息
     */
	Companys selectCompanysById(Long companyId);
	
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
	int insertCompanys(Companys companys);
	
	/**
     * 修改公司
     * 
     * @param companys 公司信息
     * @return 结果
     */
	int updateCompanys(Companys companys);
	
	/**
     * 删除公司
     * 
     * @param companyId 公司ID
     * @return 结果
     */
	int deleteCompanysById(Long companyId);
	
	/**
     * 批量删除公司
     * 
     * @param companyIds 需要删除的数据ID
     * @return 结果
     */
	int deleteCompanysByIds(Long[] companyIds);

	List<Companys> selectCompanyAll();
}