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
import com.tsingshan.infomana.domain.vo.EmployeeVo;
import com.tsingshan.system.service.ICompanysService;
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
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.service.IEmployeeService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;

import static com.tsingshan.common.base.AjaxResult.error;

/**
 * 员工 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Controller
@RequestMapping("/roster/employee")
public class EmployeeController extends BaseController
{
    private String prefix = "roster/employee";
	
	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private ISysPostService postService;

	@Autowired
	private IJobService jobService;

	@Autowired
	private ICompanysService companysService;
	
	@RequiresPermissions("roster:employee:view")
	@GetMapping()
	public String employee()
	{
	    return prefix + "/employee";
	}
	
	/**
	 * 查询员工列表
	 */
	@RequiresPermissions("roster:employee:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(EmployeeVo employeeVo)
	{
		startPage();
        List<EmployeeVo> list = employeeService.selectEmployeeVoList(employeeVo);
		return getDataTable(list);
	}
	
	/**
	 * 新增员工
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("posts", postService.selectPostAll());
		mmap.put("jobs", jobService.selectJobAll());
		mmap.put("companys",companysService.selectCompanyAll());
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存员工
	 */
	@RequiresPermissions("roster:employee:add")
	@Log(title = "员工", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Employee employee)
	{
	    try {
            employee.setCreateBy(ShiroUtils.getLoginName());
            AjaxResult result = employeeService.insertEmployee(employee);
            return result;
        } catch (Exception e) {
	        return error(e.getMessage());
        }
	}

	/**
	 * 修改员工
	 */
	@GetMapping("/edit/{employeeId}")
	public String edit(@PathVariable("employeeId") Long employeeId, ModelMap mmap)
	{
		EmployeeVo employeeVo = employeeService.selectEmployeeVoById(employeeId);
		mmap.put("employeeVo", employeeVo);
		mmap.put("posts", postService.selectPostAll());
		mmap.put("jobs", jobService.selectJobAll());
        mmap.put("companys",companysService.selectCompanyAll());
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存员工
	 */
	@RequiresPermissions("roster:employee:edit")
	@Log(title = "员工", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Employee employee)
	{
	    try {
            employee.setUpdateBy(ShiroUtils.getLoginName());
            AjaxResult result = employeeService.updateEmployee(employee);
            return result;
        } catch (Exception e) {
	        return error(e.getMessage());
        }

	}
	
	/**
	 * 删除员工
	 */
	@RequiresPermissions("roster:employee:remove")
	@Log(title = "员工", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		try{
            AjaxResult result = employeeService.deleteEmployeeByIds(ids);
            return result;
		}catch (Exception e) {
			return AjaxResult.error(e.getMessage());
		}
	}

	@Log(title = "员工", businessType = BusinessType.EXPORT)
	@RequiresPermissions("roster:employee:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(String  ids)
	{
		List<EmployeeVo> list = employeeService.selectExport(ids);
		ExcelUtil<EmployeeVo> util = new ExcelUtil<EmployeeVo>(EmployeeVo.class);
		return util.exportExcel(list, "employee");
	}

    @Log(title = "根据工号查询员工信息", businessType = BusinessType.OTHER)
    @PostMapping("/employeeNo")
    @ResponseBody
    public AjaxResult findByEmployeeNo(String employeeNo) {
        Employee employee = employeeService.selectEmployeeByEmployeeNo(employeeNo);
        return AjaxResult.success(employee);
    }


	@Override
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
