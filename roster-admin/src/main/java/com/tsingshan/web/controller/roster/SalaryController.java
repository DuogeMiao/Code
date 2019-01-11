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
import com.tsingshan.infomana.domain.Salary;
import com.tsingshan.infomana.service.ISalaryService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;

/**
 * 调薪 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Controller
@RequestMapping("/roster/salary")
public class SalaryController extends BaseController
{
    private String prefix = "roster/salary";
	
	@Autowired
	private ISalaryService salaryService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private ISysPostService postService;

	@Autowired
	private IJobService jobService;
	
	@RequiresPermissions("roster:salary:view")
	@GetMapping()
	public String salary()
	{
	    return prefix + "/salary";
	}
	
	/**
	 * 查询调薪列表
	 */
	@RequiresPermissions("roster:salary:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Salary salary)
	{
		startPage();
        List<Salary> list = salaryService.selectSalaryList(salary);
		return getDataTable(list);
	}
	
	/**
	 * 新增调薪
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("posts", postService.selectPostAll());
		mmap.put("jobs", jobService.selectJobAll());
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存调薪
	 */
	@RequiresPermissions("roster:salary:add")
	@Log(title = "调薪", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Salary salary)
	{
		salary.setCreateBy(ShiroUtils.getLoginName());
		if (StringUtils.isEmpty(salary.getJobName()) || StringUtils.isEmpty(salary.getPostName())) {
			return AjaxResult.error("岗位和职务不能为空");
		}
		AjaxResult ajaxResult = salaryService.insertSalary(salary);
		return ajaxResult;
	}

	/**
	 * 修改调薪
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Salary salary = salaryService.selectSalaryById(id);
		mmap.put("salary", salary);
		mmap.put("posts", postService.selectPostAll());
		mmap.put("jobs", jobService.selectJobAll());
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存调薪
	 */
	@RequiresPermissions("roster:salary:edit")
	@Log(title = "调薪", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Salary salary)
	{
		salary.setUpdateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = salaryService.updateSalary(salary);
		return ajaxResult;
	}
	
	/**
	 * 删除调薪
	 */
	@RequiresPermissions("roster:salary:remove")
	@Log(title = "调薪", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		AjaxResult ajaxResult = salaryService.deleteSalaryByIds(ids);
		return ajaxResult;

	}

	@Log(title = "调薪", businessType = BusinessType.EXPORT)
	@RequiresPermissions("roster:salary:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(String  ids)
	{
		List<Salary> list = salaryService.selectExport(ids);
		ExcelUtil<Salary> util = new ExcelUtil<Salary>(Salary.class);
		return util.exportExcel(list, "salary");
	}

	/**
	 * 向页面put值
	 * @param employeeId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/{employeeId}")
	public String salaryByEmpId(@PathVariable("employeeId") Integer employeeId, ModelMap mmap)
	{
		mmap.put("employeeId", employeeId);
		return prefix + "/salary";
	}

//	/**
//	 * 根据员工id回显员工信息到页面自动填充
//	 * @param employeeId
//	 * @param map
//	 * @return
//	 */
//	@GetMapping("/add/{employeeId}")
//	public String addByEmployeeId(@PathVariable("employeeId") Long employeeId, ModelMap map)
//	{
//		Employee employee = employeeService.selectEmployeeById(employeeId);
//		map.put("employee", employee);
//		map.put("employeeId", employee.getEmployeeId());
//		map.put("employeeName", employee.getEmployeeName());
//		map.put("employeeNo", employee.getEmployeeNo());
//		map.put("identityCard", employee.getIdentityCard());
//		return prefix + "/add2";
//	}

	@Override
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
