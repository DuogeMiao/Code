package com.tsingshan.web.controller.roster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.tsingshan.common.annotation.Log;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.enums.BusinessType;
import com.tsingshan.common.utils.ExcelUtil;
import com.tsingshan.framework.util.ShiroUtils;
import com.tsingshan.framework.web.page.TableDataInfo;
import com.tsingshan.infomana.domain.vo.VisaInfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.tsingshan.infomana.domain.VisaInfo;
import com.tsingshan.infomana.service.IVisaInfoService;
import com.tsingshan.web.core.base.BaseController;

/**
 * 签证 信息操作处理
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
@Controller
@RequestMapping("/roster/visaInfo")
public class VisaInfoController extends BaseController
{
    private String prefix = "roster/visaInfo";
	
	@Autowired
	private IVisaInfoService visaInfoService;
	
	@RequiresPermissions("roster:visaInfo:view")
	@GetMapping()
	public String visaInfo()
	{
	    return prefix + "/visaInfo";
	}
	
	/**
	 * 查询签证列表
	 */
	@RequiresPermissions("roster:visaInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VisaInfoVo visaInfoVo)
	{
		startPage();
        List<VisaInfoVo> list = visaInfoService.selectVisaInfoVoList(visaInfoVo);
		return getDataTable(list);
	}
	
	/**
	 * 新增签证
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存签证
	 */
	@RequiresPermissions("roster:visaInfo:add")
	@Log(title = "签证", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VisaInfo visaInfo)
	{
	    visaInfo.setCreateBy(ShiroUtils.getLoginName());
		return visaInfoService.insertVisaInfo(visaInfo);
	}

	/**
	 * 修改签证
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		VisaInfo visaInfo = visaInfoService.selectVisaInfoById(id);
		mmap.put("visaInfo", visaInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存签证
	 */
	@RequiresPermissions("roster:visaInfo:edit")
	@Log(title = "签证", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VisaInfo visaInfo)
	{
	    visaInfo.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(visaInfoService.updateVisaInfo(visaInfo));
	}
	
	/**
	 * 删除签证
	 */
	@RequiresPermissions("roster:visaInfo:remove")
	@Log(title = "签证", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(visaInfoService.deleteVisaInfoByIds(ids));
	}


    @Log(title = "签证", businessType = BusinessType.EXPORT)
    @RequiresPermissions("roster:visaInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String  ids)
    {
        List<VisaInfoVo> list = visaInfoService.exportVisaInfoVo(ids);
        ExcelUtil<VisaInfoVo> util = new ExcelUtil<VisaInfoVo>(VisaInfoVo.class);
        return util.exportExcel(list, "VisaInfoVo");
    }


    @Override
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
}
