package com.tsingshan.infomana.service.Impl;

import java.util.List;

import com.tsingshan.common.support.Convert;
import com.tsingshan.common.utils.StringUtils;
import com.tsingshan.infomana.domain.vo.PassportInfoVo;
import com.tsingshan.infomana.mapper.PassportInfoVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.PassportInfoMapper;
import com.tsingshan.infomana.domain.PassportInfo;
import com.tsingshan.infomana.service.IPassportInfoService;


/**
 * 护照 服务层实现
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
@Service
public class PassportInfoServiceImpl implements IPassportInfoService 
{
	@Autowired
	private PassportInfoMapper passportInfoMapper;

	@Autowired
    private PassportInfoVoMapper passportInfoVoMapper;

	/**
     * 查询护照信息
     * 
     * @param id 护照ID
     * @return 护照信息
     */
    @Override
	public PassportInfo selectPassportInfoById(Long id)
	{
	    return passportInfoMapper.selectPassportInfoById(id);
	}
	
	/**
     * 查询护照列表
     * 
     * @param passportInfo 护照信息
     * @return 护照集合
     */
	@Override
	public List<PassportInfo> selectPassportInfoList(PassportInfo passportInfo)
	{
	    return passportInfoMapper.selectPassportInfoList(passportInfo);
	}

    @Override
    public List<PassportInfo> selectPassportInfoAllByState(String state) {
        return passportInfoMapper.selectPassportInfoAllByState(state);
    }

    @Override
	public List<PassportInfoVo> selectPassportInfoVoList(PassportInfoVo passportInfoVo)
	{
	    return passportInfoVoMapper.selectPassportInfoVoList(passportInfoVo);
	}
	
    /**
     * 新增护照
     * 
     * @param passportInfo 护照信息
     * @return 结果
     */
	@Override
	public int insertPassportInfo(PassportInfo passportInfo)
	{
	    return passportInfoMapper.insertPassportInfo(passportInfo);
	}
	
	/**
     * 修改护照
     * 
     * @param passportInfo 护照信息
     * @return 结果
     */
	@Override
	public int updatePassportInfo(PassportInfo passportInfo)
	{
	    return passportInfoMapper.updatePassportInfo(passportInfo);
	}

	/**
     * 删除护照对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePassportInfoByIds(String ids)
	{
		return passportInfoMapper.deletePassportInfoByIds(Convert.toLongArray(ids));
	}

    @Override
    public String checkPassportNoUnique(String passportNo) {
        PassportInfo passportInfo = passportInfoMapper.checkPassportNoUnique(passportNo);
        if (StringUtils.isNotNull(passportInfo)) {
            return "1";
        }
        return "0";
    }

    @Override
    public List<PassportInfoVo> exportPassportInfo(String ids) {
        return passportInfoVoMapper.exportPassportInfoVo(Convert.toLongArray(ids));
    }


}
