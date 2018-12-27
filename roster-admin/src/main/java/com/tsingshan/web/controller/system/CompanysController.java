package com.tsingshan.web.controller.system;

import java.util.List;
import com.tsingshan.common.annotation.Log;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.enums.BusinessType;
import com.tsingshan.framework.util.ShiroUtils;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tsingshan.system.domain.Companys;
import com.tsingshan.system.service.ICompanysService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;


/**
 * 公司 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-12-20
 */
@Controller
@RequestMapping("/system/companys")
public class CompanysController extends BaseController
{
    private String prefix = "system/companys";
	
	@Autowired
	private ICompanysService companysService;
	
	@RequiresPermissions("system:companys:view")
	@GetMapping()
	public String companys()
	{
	    return prefix + "/companys";
	}
	
	/**
	 * 查询公司列表
	 */
	@RequiresPermissions("system:companys:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Companys companys)
	{
		startPage();
        List<Companys> list = companysService.selectCompanysList(companys);
		return getDataTable(list);
	}
	
	/**
	 * 新增公司
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存公司
	 */
	@RequiresPermissions("system:companys:add")
	@Log(title = "公司", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Companys companys)
	{
	    companys.setCreateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = companysService.insertCompanys(companys);
		return ajaxResult;
	}

	/**
	 * 修改公司
	 */
	@GetMapping("/edit/{companyId}")
	public String edit(@PathVariable("companyId") Long companyId, ModelMap mmap)
	{
		Companys companys = companysService.selectCompanysById(companyId);
		mmap.put("companys", companys);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存公司
	 */
	@RequiresPermissions("system:companys:edit")
	@Log(title = "公司", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Companys companys)
	{
	    companys.setUpdateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = companysService.updateCompanys(companys);
		return ajaxResult;
	}

	/**
	 * 查找公司
	 */
	@Log(title = "根据Id获取公司信息", businessType = BusinessType.OTHER)
	@PostMapping("/companyId")
	@ResponseBody
	public AjaxResult findCompanyById(Long companyId)
	{
		Companys companys = companysService.selectCompanysById(companyId);
		return AjaxResult.success(companys);
	}
	
	/**
	 * 删除公司
	 */
	@RequiresPermissions("system:companys:remove")
	@Log(title = "公司", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		AjaxResult ajaxResult = companysService.deleteCompanysByIds(ids);
		return ajaxResult;
	}
	
}
