package com.tsingshan.system.service.impl;


import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import com.tsingshan.system.domain.Job;
import com.tsingshan.system.mapper.JobMapper;
import com.tsingshan.system.service.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职务 服务层实现
 * 
 * @author tsingshan
 * @date 2018-12-12
 */
@Service
public class JobServiceImpl implements IJobService
{
	private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

	@Autowired
	private JobMapper jobMapper;

	/**
     * 查询职务信息
     * 
     * @param jobId 职务ID
     * @return 职务信息
     */
    @Override
	public Job selectJobById(long jobId)
	{
	    return jobMapper.selectJobById(jobId);
	}
	
	/**
     * 查询职务列表
     * 
     * @param job 职务信息
     * @return 职务集合
     */
	@Override
	public List<Job> selectJobList(Job job)
	{
	    return jobMapper.selectJobList(job);
	}
	
    /**
     * 新增职务
     * 
     * @param job 职务信息
     * @return 结果
     */
	@Override
	public AjaxResult insertJob(Job job)
	{
        try {
            jobMapper.insertJob(job);
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("添加职务信息异常{}", e.getMessage());
            return AjaxResult.error("添加异常");
        }
    }
	
	/**
     * 修改职务
     * 
     * @param job 职务信息
     * @return 结果
     */
	@Override
	public AjaxResult updateJob(Job job)
	{
        try {
            jobMapper.updateJob(job);
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("更新职务信息异常{}", e.getMessage());
            return AjaxResult.error("更新异常");
        }
	}

	/**
     * 删除职务对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deleteJobByIds(String ids)
	{
        try {
            jobMapper.deleteJobByIds(Convert.toLongArray(ids));
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("根据ID删除职务信息异常{}", e.getMessage());
            return AjaxResult.error("删除异常");
        }
	}

	/**
	 * 查询所有职务
	 * @return  职务列表
	 */
	@Override
	public List<Job> selectJobAll() {
		return jobMapper.selectJobAll();
	}

}
