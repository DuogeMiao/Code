package com.tsingshan.infomana.service.Impl;

import java.util.List;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.VisaMapper;
import com.tsingshan.infomana.domain.Visa;
import com.tsingshan.infomana.service.IVisaService;


/**
 * 护照签证 服务层实现
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Service
public class VisaServiceImpl implements IVisaService 
{
	private static final Logger logger = LoggerFactory.getLogger(VisaServiceImpl.class);

	@Autowired
	private VisaMapper visaMapper;


	/**
     * 查询护照签证信息
     * 
     * @param id 护照签证ID
     * @return 护照签证信息
     */
    @Override
	public Visa selectVisaById(long id)
	{
	    return visaMapper.selectVisaById(id);
	}
	
	/**
     * 查询护照签证列表
     * 
     * @param visa 护照签证信息
     * @return 护照签证集合
     */
	@Override
	public List<Visa> selectVisaList(Visa visa)
	{
	    return visaMapper.selectVisaList(visa);
	}
	
    /**
     * 新增护照签证
     * 
     * @param visa 护照签证信息
     * @return 结果
     */
	@Override
	public AjaxResult insertVisa(Visa visa)
	{
		try {
//			Visa vs = new Visa();
//			vs.setPassportNo(visa.getPassportNo());
//			List<Visa> visas = visaMapper.selectVisaList(vs);
//			if (visas.size() > 0) {
//				return AjaxResult.error("已经有该护照信息");
//			}
			//更新旧护照信息状态
            Visa visaByEmployeeNoStatus= visaMapper.selectVisaByEmployeeNoState(visa.getEmployeeNo(), "0");
			if (visaByEmployeeNoStatus != null) {
                visaByEmployeeNoStatus.setState("1");
                visa.setVisaNo(visaByEmployeeNoStatus.getVisaNo() + 1);
                updateVisa(visaByEmployeeNoStatus);
            } else {
                visa.setVisaNo(1);
            }
            visa.setState("0");
            visaMapper.insertVisa(visa);
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("添加护照信息异常{}", e.getMessage());
			return AjaxResult.error("添加异常");
		}
	}
	
	/**
     * 修改护照签证
     * 
     * @param visa 护照签证信息
     * @return 结果
     */
	@Override
	public AjaxResult updateVisa(Visa visa)
	{
		try {
			visaMapper.updateVisa(visa);
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("更新护照信息异常{}", e.getMessage());
			return AjaxResult.error("更新异常");
		}
	}

	/**
     * 删除护照签证对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deleteVisaByIds(String ids)
	{
		try {
			visaMapper.deleteVisaByIds(Convert.toLongArray(ids));
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("根据ID删除护照信息异常{}", e.getMessage());
			return AjaxResult.error("删除异常");
		}
	}

	@Override
	public List<Visa> selectExport(String ids) {
		return visaMapper.selectExport(Convert.toLongArray(ids));
	}

    @Override
    public Visa selectVisaByEmployeeNoState(String employeeNo, String state) {
        return visaMapper.selectVisaByEmployeeNoState(employeeNo, state);
    }

}
