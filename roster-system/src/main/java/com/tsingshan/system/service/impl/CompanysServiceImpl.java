package com.tsingshan.system.service.impl;

import java.util.List;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.system.mapper.CompanysMapper;
import com.tsingshan.system.domain.Companys;
import com.tsingshan.system.service.ICompanysService;


/**
 * 公司 服务层实现
 * 
 * @author tsingshan
 * @date 2018-12-20
 */
@Service
public class CompanysServiceImpl implements ICompanysService 
{
	private static final Logger logger = LoggerFactory.getLogger(CompanysServiceImpl.class);

	@Autowired
	private CompanysMapper companysMapper;

	/**
     * 查询公司信息
     * 
     * @param companyId 公司ID
     * @return 公司信息
     */
    @Override
	public Companys selectCompanysById(long companyId)
	{
	    return companysMapper.selectCompanysById(companyId);
	}

    @Override
    public Companys selectCompanysByCompanyCode(String companyCode) {
        return companysMapper.selectCompanysByCompanyCode(companyCode);
    }

    /**
     * 查询公司列表
     * 
     * @param companys 公司信息
     * @return 公司集合
     */
	@Override
	public List<Companys> selectCompanysList(Companys companys)
	{
	    return companysMapper.selectCompanysList(companys);
	}
	
    /**
     * 新增公司
     * 
     * @param companys 公司信息
     * @return 结果
     */
	@Override
	public AjaxResult insertCompanys(Companys companys)
	{
		try {
			companysMapper.insertCompanys(companys);
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("添加公司信息异常{}", e.getMessage());
			return AjaxResult.error("添加异常");
		}
	}
	
	/**
     * 修改公司
     * 
     * @param companys 公司信息
     * @return 结果
     */
	@Override
	public AjaxResult updateCompanys(Companys companys)
	{
		try {
			companysMapper.updateCompanys(companys);
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("更新公司信息异常{}", e.getMessage());
			return AjaxResult.error("更新异常");
		}
	}

	/**
     * 删除公司对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deleteCompanysByIds(String ids)
	{
		try {
			companysMapper.deleteCompanysByIds(Convert.toLongArray(ids));
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("根据Id删除公司信息异常{}", e.getMessage());
			return AjaxResult.error("删除异常");
		}

	}

	@Override
	public List<Companys> selectCompanyAll() {
		return companysMapper.selectCompanyAll();
	}

}
