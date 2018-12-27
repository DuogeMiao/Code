package com.tsingshan.web.controller.system;

import com.tsingshan.common.annotation.Log;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.enums.BusinessType;
import com.tsingshan.framework.util.ShiroUtils;
import com.tsingshan.system.domain.Job;
import com.tsingshan.system.service.IJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;

import java.util.List;

/**
 * 职务 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-12-12
 */
@Controller
@RequestMapping("/system/job")
public class JobController extends BaseController
{
    private String prefix = "system/job";
	
	@Autowired
	private IJobService jobService;
	
	@RequiresPermissions("system:job:view")
	@GetMapping()
	public String job()
	{
	    return prefix + "/job";
	}
	
	/**
	 * 查询职务列表
	 */
	@RequiresPermissions("system:job:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Job job)
	{
		startPage();
        List<Job> list = jobService.selectJobList(job);
		return getDataTable(list);
	}
	
	/**
	 * 新增职务
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存职务
	 */
	@RequiresPermissions("system:job:add")
	@Log(title = "职务", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Job job)
	{
		job.setCreateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = jobService.insertJob(job);
		return ajaxResult;
	}

	/**
	 * 修改职务
	 */
	@GetMapping("/edit/{jobId}")
	public String edit(@PathVariable("jobId") Long jobId, ModelMap mmap)
	{
		Job job = jobService.selectJobById(jobId);
		mmap.put("job", job);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存职务
	 */
	@RequiresPermissions("system:job:edit")
	@Log(title = "职务", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Job job)
	{
		job.setUpdateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = jobService.updateJob(job);
		return ajaxResult;
	}
	
	/**
	 * 删除职务
	 */
	@RequiresPermissions("system:job:remove")
	@Log(title = "职务", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		AjaxResult ajaxResult = jobService.deleteJobByIds(ids);
		return ajaxResult;
	}

	/**
	 * 根据职务id查询信息
	 */
	@Log(title = "根据职务id查询信息", businessType = BusinessType.OTHER)
	@PostMapping("/jobId")
	@ResponseBody
	public AjaxResult findJobById(Long jobId)
	{
		Job job = jobService.selectJobById(jobId);
		return AjaxResult.success(job);
	}

	
}
