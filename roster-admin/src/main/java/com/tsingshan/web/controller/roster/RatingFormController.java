package com.tsingshan.web.controller.roster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.tsingshan.common.annotation.Log;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.enums.BusinessType;
import com.tsingshan.common.utils.ExcelUtil;
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
import com.tsingshan.infomana.domain.RatingForm;
import com.tsingshan.infomana.service.IRatingFormService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;

/**
 * 员工考核 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-12-28
 */
@Controller
@RequestMapping("/roster/ratingForm")
public class RatingFormController extends BaseController
{
    private String prefix = "roster/ratingForm";
	
	@Autowired
	private IRatingFormService ratingFormService;
	
	@RequiresPermissions("roster:ratingForm:view")
	@GetMapping()
	public String ratingForm()
	{
	    return prefix + "/ratingForm";
	}
	
	/**
	 * 查询员工考核列表
	 */
	@RequiresPermissions("roster:ratingForm:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(RatingForm ratingForm)
	{
		startPage();
        List<RatingForm> list = ratingFormService.selectRatingFormList(ratingForm);
		return getDataTable(list);
	}
	
	/**
	 * 新增员工考核
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存员工考核
	 */
	@RequiresPermissions("roster:ratingForm:add")
	@Log(title = "员工考核", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(RatingForm ratingForm)
	{
	    ratingForm.setCreateBy(ShiroUtils.getLoginName());
        AjaxResult ajaxResult = ratingFormService.insertRatingForm(ratingForm);
        return ajaxResult;
	}

	/**
	 * 修改员工考核
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		RatingForm ratingForm = ratingFormService.selectRatingFormById(id);
		mmap.put("ratingForm", ratingForm);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存员工考核
	 */
	@RequiresPermissions("roster:ratingForm:edit")
	@Log(title = "员工考核", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(RatingForm ratingForm)
	{
	    ratingForm.setUpdateBy(ShiroUtils.getLoginName());
        AjaxResult ajaxResult = ratingFormService.updateRatingForm(ratingForm);
        return ajaxResult;
	}
	
	/**
	 * 删除员工考核
	 */
	@RequiresPermissions("roster:ratingForm:remove")
	@Log(title = "员工考核", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
        AjaxResult ajaxResult = ratingFormService.deleteRatingFormByIds(ids);
        return ajaxResult;
	}

    @Log(title = "员工考核", businessType = BusinessType.EXPORT)
    @RequiresPermissions("roster:ratingForm:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String  ids)
    {
        List<RatingForm> list = ratingFormService.exportRatingForm(ids);
        ExcelUtil<RatingForm> util = new ExcelUtil<RatingForm>(RatingForm.class);
        return util.exportExcel(list, "RatingForm");
    }

    @Override
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
}
