package com.tsingshan.infomana.service.Impl;

import java.util.List;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.GradeMapper;
import com.tsingshan.infomana.domain.Grade;
import com.tsingshan.infomana.service.IGradeService;

/**
 * 级档 服务层实现
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Service
public class GradeServiceImpl implements IGradeService 
{
    private static final Logger logger = LoggerFactory.getLogger(GradeServiceImpl.class);

	@Autowired
	private GradeMapper gradeMapper;

	/**
     * 查询级档信息
     * 
     * @param id 级档ID
     * @return 级档信息
     */
    @Override
	public Grade selectGradeById(int id)
	{
	    return gradeMapper.selectGradeById(id);
	}
	
	/**
     * 查询级档列表
     * 
     * @param grade 级档信息
     * @return 级档集合
     */
	@Override
	public List<Grade> selectGradeList(Grade grade)
	{
	    return gradeMapper.selectGradeList(grade);
	}
	
    /**
     * 新增级档
     * 
     * @param grade 级档信息
     * @return 结果
     */
	@Override
	public AjaxResult insertGrade(Grade grade)
	{
		try {
            gradeMapper.insertGrade(grade);
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("添加级档信息异常{}", e.getMessage());
            return AjaxResult.error("添加异常");
		}
	}
	
	/**
     * 修改级档
     * 
     * @param grade 级档信息
     * @return 结果
     */
	@Override
	public AjaxResult updateGrade(Grade grade)
	{
        try {
            gradeMapper.updateGrade(grade);
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("更新级档信息异常{}", e.getMessage());
            return AjaxResult.error("更新异常");
        }
    }

	/**
     * 删除级档对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deleteGradeByIds(String ids)
	{
        try {
            gradeMapper.deleteGradeByIds(Convert.toIntArray(ids));
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("根据ID删除级档信息异常{}", e.getMessage());
            return AjaxResult.error("删除异常");
        }
    }

	@Override
	public List<Grade> selectExport(String ids) {
		return gradeMapper.selectExport(Convert.toIntArray(ids));
	}

}
