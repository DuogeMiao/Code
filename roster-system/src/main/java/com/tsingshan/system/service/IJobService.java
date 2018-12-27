package com.tsingshan.system.service;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.system.domain.Job;
import java.util.List;

/**
 * 职务 服务层
 * 
 * @author tsingshan
 * @date 2018-12-12
 */
public interface IJobService 
{
	/**
     * 查询职务信息
     * 
     * @param jobId 职务ID
     * @return 职务信息
     */
	Job selectJobById(long jobId);
	
	/**
     * 查询职务列表
     * 
     * @param job 职务信息
     * @return 职务集合
     */
	List<Job> selectJobList(Job job);
	
	/**
     * 新增职务
     * 
     * @param job 职务信息
     * @return 结果
     */
	AjaxResult insertJob(Job job);
	
	/**
     * 修改职务
     * 
     * @param job 职务信息
     * @return 结果
     */
	AjaxResult updateJob(Job job);
		
	/**
     * 删除职务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	AjaxResult deleteJobByIds(String ids);

	/**
	 * 查询所有职务
	 * @return  职务列表
	 */
    List<Job> selectJobAll();
}
