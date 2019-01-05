package com.tsingshan.infomana.service.Impl;

import java.util.List;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import com.tsingshan.infomana.domain.vo.VisaInfoVo;
import com.tsingshan.infomana.mapper.VisaInfoVoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.VisaInfoMapper;
import com.tsingshan.infomana.domain.VisaInfo;
import com.tsingshan.infomana.service.IVisaInfoService;


/**
 * 签证 服务层实现
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
@Service
public class VisaInfoServiceImpl implements IVisaInfoService 
{
    private static final Logger logger = LoggerFactory.getLogger(VisaInfoServiceImpl.class);

	@Autowired
	private VisaInfoMapper visaInfoMapper;

	@Autowired
    private VisaInfoVoMapper visaInfoVoMapper;

	/**
     * 查询签证信息
     * 
     * @param id 签证ID
     * @return 签证信息
     */
    @Override
	public VisaInfo selectVisaInfoById(Long id)
	{
	    return visaInfoMapper.selectVisaInfoById(id);
	}
	
	/**
     * 查询签证列表
     * 
     * @param visaInfo 签证信息
     * @return 签证集合
     */
	@Override
	public List<VisaInfo> selectVisaInfoList(VisaInfo visaInfo)
	{
	    return visaInfoMapper.selectVisaInfoList(visaInfo);
	}

    @Override
    public List<VisaInfoVo> selectVisaInfoVoList(VisaInfoVo visaInfoVo) {
        return visaInfoVoMapper.selectVisaInfoVoList(visaInfoVo);
    }

    /**
     * 新增签证
     * 
     * @param visaInfo 签证信息
     * @return 结果
     */
	@Override
	public AjaxResult insertVisaInfo(VisaInfo visaInfo)
	{
        try {
            VisaInfo vs = new VisaInfo();
            vs.setPassportId(visaInfo.getPassportId());
            vs.setState("0");
            List<VisaInfo> list = visaInfoMapper.selectVisaInfoList(vs);
            if (list.size() > 0) {
                VisaInfo oldVisaInfo = list.get(0);
                oldVisaInfo.setState("1");
                visaInfo.setVisaNo(oldVisaInfo.getVisaNo() + 1);
                visaInfoMapper.updateVisaInfo(oldVisaInfo);
            } else {
                visaInfo.setVisaNo(1);
            }
            visaInfoMapper.insertVisaInfo(visaInfo);
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("新增异常{}", e.getMessage());
            return AjaxResult.error("新增异常");
        }
    }
	
	/**
     * 修改签证
     * 
     * @param visaInfo 签证信息
     * @return 结果
     */
	@Override
	public int updateVisaInfo(VisaInfo visaInfo)
	{
	    return visaInfoMapper.updateVisaInfo(visaInfo);
	}

	/**
     * 删除签证对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVisaInfoByIds(String ids)
	{
		return visaInfoMapper.deleteVisaInfoByIds(Convert.toLongArray(ids));
	}

    @Override
    public List<VisaInfoVo> exportVisaInfoVo(String ids) {
        return visaInfoVoMapper.exportVisaInfoVo(Convert.toLongArray(ids));
    }

}
