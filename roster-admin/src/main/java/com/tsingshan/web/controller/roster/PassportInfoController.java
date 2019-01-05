package com.tsingshan.web.controller.roster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.tsingshan.common.annotation.Log;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.enums.BusinessType;
import com.tsingshan.common.utils.ExcelUtil;
import com.tsingshan.framework.util.ShiroUtils;
import com.tsingshan.infomana.domain.vo.PassportInfoVo;
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
import com.tsingshan.infomana.domain.PassportInfo;
import com.tsingshan.infomana.service.IPassportInfoService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;


/**
 * 护照 信息操作处理
 * 
 * @author tsingshan
 * @date 2019-01-04
 */
@Controller
@RequestMapping("/roster/passportInfo")
public class PassportInfoController extends BaseController
{
    private String prefix = "roster/passportInfo";
	
	@Autowired
	private IPassportInfoService passportInfoService;
	
	@RequiresPermissions("roster:passportInfo:view")
	@GetMapping()
	public String passportInfo()
	{
	    return prefix + "/passportInfo";
	}
	
	/**
	 * 查询护照列表
	 */
	@RequiresPermissions("roster:passportInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PassportInfoVo passportInfoVo)
	{
		startPage();
        List<PassportInfoVo> list = passportInfoService.selectPassportInfoVoList(passportInfoVo);
		return getDataTable(list);
	}
	
	/**
	 * 新增护照
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存护照
	 */
	@RequiresPermissions("roster:passportInfo:add")
	@Log(title = "护照", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PassportInfo passportInfo)
	{
	    passportInfo.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(passportInfoService.insertPassportInfo(passportInfo));
	}

	/**
	 * 修改护照
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		PassportInfo passportInfo = passportInfoService.selectPassportInfoById(id);
		mmap.put("passportInfo", passportInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存护照
	 */
	@RequiresPermissions("roster:passportInfo:edit")
	@Log(title = "护照", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PassportInfo passportInfo)
	{
	    passportInfo.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(passportInfoService.updatePassportInfo(passportInfo));
	}
	
	/**
	 * 删除护照
	 */
	@RequiresPermissions("roster:passportInfo:remove")
	@Log(title = "护照", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(passportInfoService.deletePassportInfoByIds(ids));
	}


    @Log(title = "护照", businessType = BusinessType.EXPORT)
    @RequiresPermissions("roster:ratingForm:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(String  ids)
    {
        List<PassportInfoVo> list = passportInfoService.exportPassportInfo(ids);
        ExcelUtil<PassportInfoVo> util = new ExcelUtil<PassportInfoVo>(PassportInfoVo.class);
        return util.exportExcel(list, "PassportInfoVo");
    }

    /**
     * 校验护照号
     * @return
     */
	@PostMapping("/checkPassportNoUnique")
    @ResponseBody
    public String checkPassportNoUnique(String passportNo) {
	    return passportInfoService.checkPassportNoUnique(passportNo);
    }


    @Override
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
}
