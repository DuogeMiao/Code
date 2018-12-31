package com.tsingshan.infomana.service.Impl;

import java.util.List;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.RatingFormMapper;
import com.tsingshan.infomana.domain.RatingForm;
import com.tsingshan.infomana.service.IRatingFormService;

/**
 * 员工考核 服务层实现
 * 
 * @author tsingshan
 * @date 2018-12-28
 */
@Service
public class RatingFormServiceImpl implements IRatingFormService 
{
    private static final Logger logger = LoggerFactory.getLogger(RatingFormServiceImpl.class);

	@Autowired
	private RatingFormMapper ratingFormMapper;

	/**
     * 查询员工考核信息
     * 
     * @param id 员工考核ID
     * @return 员工考核信息
     */
    @Override
	public RatingForm selectRatingFormById(long id)
	{
	    return ratingFormMapper.selectRatingFormById(id);
	}
	
	/**
     * 查询员工考核列表
     * 
     * @param ratingForm 员工考核信息
     * @return 员工考核集合
     */
	@Override
	public List<RatingForm> selectRatingFormList(RatingForm ratingForm)
	{
	    return ratingFormMapper.selectRatingFormList(ratingForm);
	}
	
    /**
     * 新增员工考核
     * 
     * @param ratingForm 员工考核信息
     * @return 结果
     */
	@Override
	public AjaxResult insertRatingForm(RatingForm ratingForm)
	{
        try {
            ratingFormMapper.insertRatingForm(ratingForm);
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("添加信息异常{}",e.getMessage());
            return AjaxResult.error("添加异常");
        }
    }
	
	/**
     * 修改员工考核
     * 
     * @param ratingForm 员工考核信息
     * @return 结果
     */
	@Override
	public AjaxResult updateRatingForm(RatingForm ratingForm)
	{
        try {
            ratingFormMapper.updateRatingForm(ratingForm);
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("更新信息异常");
            return AjaxResult.error("更新异常");
        }
    }

	/**
     * 删除员工考核对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deleteRatingFormByIds(String ids)
	{

        try {
            ratingFormMapper.deleteRatingFormByIds(Convert.toLongArray(ids));
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("根据ID批量删除异常");
            return AjaxResult.error("删除异常");
        }
    }

    @Override
    public List<RatingForm> exportRatingForm(String ids) {
        return ratingFormMapper.selectRatingForm(Convert.toLongArray(ids));
    }

}
