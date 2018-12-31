package com.tsingshan.web.controller.roster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tsingshan.common.annotation.Log;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.enums.BusinessType;
import com.tsingshan.common.utils.ExcelUtil;
import com.tsingshan.common.utils.StringUtils;
import com.tsingshan.framework.util.ShiroUtils;
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.domain.vo.EmployeeVo;
import com.tsingshan.infomana.service.IEmployeeService;
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
import com.tsingshan.infomana.domain.Visa;
import com.tsingshan.infomana.service.IVisaService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;

/**
 * 护照签证 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Controller
@RequestMapping("/roster/visa")
public class VisaController extends BaseController
{
    private String prefix = "roster/visa";
	
	@Autowired
	private IVisaService visaService;

	@Autowired
	private IEmployeeService employeeService;
	
	@RequiresPermissions("roster:visa:view")
	@GetMapping()
	public String visa()
	{
		return prefix + "/visa";
	}

	/**
	 * 查询护照签证列表
	 */
	@RequiresPermissions("roster:visa:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Visa visa)
	{
		startPage();
		List<Visa> list = visaService.selectVisaList(visa);
		return getDataTable(list);
	}
	
	/**
	 * 新增护照签证
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存护照签证
	 */
	@RequiresPermissions("roster:visa:add")
	@Log(title = "护照签证", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Visa visa)
	{
		visa.setCreateBy(ShiroUtils.getLoginName());
        if (null == visa.getEmployeeNo() || "".equals(visa.getEmployeeNo())) {
            return AjaxResult.error("工号不能为空");
        }
		AjaxResult ajaxResult = visaService.insertVisa(visa);
		return ajaxResult;
	}

	/**
	 * 修改护照签证
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Visa visa = visaService.selectVisaById(id);
		mmap.put("visa", visa);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存护照签证
	 */
	@RequiresPermissions("roster:visa:edit")
	@Log(title = "护照签证", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Visa visa)
	{
		visa.setUpdateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = visaService.updateVisa(visa);
		return ajaxResult;
	}

	@Log(title = "护照签证", businessType = BusinessType.EXPORT)
	@RequiresPermissions("roster:visa:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(String  ids)
	{
		List<Visa> list = visaService.selectExport(ids);
		ExcelUtil<Visa> util = new ExcelUtil<Visa>(Visa.class);
		return util.exportExcel(list, "visa");
	}
	
	/**
	 * 删除护照签证
	 */
	@RequiresPermissions("roster:visa:remove")
	@Log(title = "护照签证", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		AjaxResult ajaxResult = visaService.deleteVisaByIds(ids);
		return ajaxResult;
	}

	/**
	 * 向页面put值
	 * @param employeeId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/{employeeId}")
	public String visaByEmpId(@PathVariable("employeeId") Integer employeeId, ModelMap mmap)
	{
		mmap.put("employeeId", employeeId);
		return prefix + "/visa";
	}

	/**
	 * 根据员工id回显员工信息到页面自动填充
	 * @param employeeId
	 * @param map
	 * @return
	 */
	@GetMapping("/add/{employeeId}")
	public String addByEmployeeId(@PathVariable("employeeId") Long employeeId, ModelMap map)
	{
		EmployeeVo employee = employeeService.selectEmployeeVoById(employeeId);
		map.put("employeeId", employee.getEmployeeId());
		map.put("employeeName", employee.getEmployeeName());
		map.put("employeeNo", employee.getEmployeeNo());
		map.put("identityCard", employee.getIdentityCard());
		return prefix + "/add2";
	}

	@PostMapping("/employeeNo")
    @ResponseBody
    public AjaxResult findVisaByEmployeeNo(String employeeNo) {
	    Visa visa = visaService.selectVisaByEmployeeNoState(employeeNo,"0");
	    return AjaxResult.success(visa);
    }

	@Override
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
