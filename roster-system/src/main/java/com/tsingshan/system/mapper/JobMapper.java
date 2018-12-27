package com.tsingshan.system.mapper;


import com.tsingshan.system.domain.Job;

import java.util.List;

/**
 * 职务 数据层
 * 
 * @author tsingshan
 * @date 2018-12-12
 */
public interface JobMapper 
{
	/**
     * 查询职务信息
     * 
     * @param jobId 职务ID
     * @return 职务信息
     */
	Job selectJobById(Long jobId);
	
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
	int insertJob(Job job);
	
	/**
     * 修改职务
     * 
     * @param job 职务信息
     * @return 结果
     */
	int updateJob(Job job);
	
	/**
     * 删除职务
     * 
     * @param jobId 职务ID
     * @return 结果
     */
	int deleteJobById(Long jobId);
	
	/**
     * 批量删除职务
     * 
     * @param jobIds 需要删除的数据ID
     * @return 结果
     */
	int deleteJobByIds(Long[] jobIds);

	/**
	 * 查询所有职务
	 * @return  职务列表
	 */
    List<Job> selectJobAll();
}