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
import com.tsingshan.infomana.domain.Grade;
import com.tsingshan.infomana.service.IGradeService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;


/**
 * 级档 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Controller
@RequestMapping("/roster/grade")
public class GradeController extends BaseController
{
    private String prefix = "roster/grade";
	
	@Autowired
	private IGradeService gradeService;
	
	@RequiresPermissions("roster:grade:view")
	@GetMapping()
	public String grade()
	{
	    return prefix + "/grade";
	}
	
	/**
	 * 查询级档列表
	 */
	@RequiresPermissions("roster:grade:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Grade grade)
	{
		startPage();
        List<Grade> list = gradeService.selectGradeList(grade);
		return getDataTable(list);
	}
	
	/**
	 * 新增级档
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存级档
	 */
	@RequiresPermissions("roster:grade:add")
	@Log(title = "级档", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Grade grade)
	{
		grade.setCreateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = gradeService.insertGrade(grade);
		return ajaxResult;
	}

	/**
	 * 修改级档
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Grade grade = gradeService.selectGradeById(id);
		mmap.put("grade", grade);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存级档
	 */
	@RequiresPermissions("roster:grade:edit")
	@Log(title = "级档", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Grade grade)
	{
		grade.setUpdateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = gradeService.updateGrade(grade);
		return ajaxResult;
	}
	
	/**
	 * 删除级档
	 */
	@RequiresPermissions("roster:grade:remove")
	@Log(title = "级档", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		AjaxResult ajaxResult = gradeService.deleteGradeByIds(ids);
		return ajaxResult;
	}

	@Log(title = "级档", businessType = BusinessType.EXPORT)
	@RequiresPermissions("roster:grade:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(String  ids)
	{
		List<Grade> list = gradeService.selectExport(ids);
		ExcelUtil<Grade> util = new ExcelUtil<Grade>(Grade.class);
		return util.exportExcel(list, "grade");
	}


	@Override
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
