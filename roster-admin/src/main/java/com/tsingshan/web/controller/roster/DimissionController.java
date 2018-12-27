package com.tsingshan.web.controller.roster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tsingshan.common.annotation.Log;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.enums.BusinessType;
import com.tsingshan.common.utils.ExcelUtil;
import com.tsingshan.common.utils.StringUtils;
import com.tsingshan.framework.util.ShiroUtils;
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.domain.vo.EmployeeVo;
import com.tsingshan.infomana.service.IEmployeeService;
import com.tsingshan.system.service.IJobService;
import com.tsingshan.system.service.ISysPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tsingshan.infomana.domain.Dimission;
import com.tsingshan.infomana.service.IDimissionService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;

/**
 * 离职 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Controller
@RequestMapping("/roster/dimission")
public class DimissionController extends BaseController
{
    private String prefix = "roster/dimission";
	
	@Autowired
	private IDimissionService dimissionService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private ISysPostService postService;

	@Autowired
	private IJobService jobService;
	
	@RequiresPermissions("roster:dimission:view")
	@GetMapping()
	public String dimission()
	{
	    return prefix + "/dimission";
	}
	
	/**
	 * 查询离职列表
	 */
	@RequiresPermissions("roster:dimission:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Dimission dimission)
	{
		startPage();
        List<Dimission> list = dimissionService.selectDimissionList(dimission);
		return getDataTable(list);
	}
	
	/**
	 * 新增离职
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("posts", postService.selectPostAll());
		mmap.put("jobs", jobService.selectJobAll());
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存离职
	 */
	@RequiresPermissions("roster:dimission:add")
	@Log(title = "离职", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Dimission dimission)
	{
		dimission.setCreateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = dimissionService.insertDimission(dimission);
		return ajaxResult;
	}

	/**
	 * 修改离职
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Dimission dimission = dimissionService.selectDimissionById(id);
		mmap.put("dimission", dimission);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存离职
	 */
	@RequiresPermissions("roster:dimission:edit")
	@Log(title = "离职", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Dimission dimission)
	{
		dimission.setUpdateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = dimissionService.updateDimission(dimission);
		return ajaxResult;
	}
	
	/**
	 * 删除离职
	 */
	@RequiresPermissions("roster:dimission:remove")
	@Log(title = "离职", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		AjaxResult ajaxResult = dimissionService.deleteDimissionByIds(ids);
		return ajaxResult;

	}

	@Log(title = "离职", businessType = BusinessType.EXPORT)
	@RequiresPermissions("roster:dimission:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(String  ids)
	{
		List<Dimission> list = dimissionService.selectExport(ids);
		ExcelUtil<Dimission> util = new ExcelUtil<Dimission>(Dimission.class);
		return util.exportExcel(list, "dimission");
	}

	@Override
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
