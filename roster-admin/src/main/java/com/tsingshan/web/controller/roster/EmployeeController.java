package com.tsingshan.web.controller.roster;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tsingshan.common.annotation.Log;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.enums.BusinessType;
import com.tsingshan.common.utils.ExcelUtil;
import com.tsingshan.framework.util.ShiroUtils;
import com.tsingshan.system.service.ICompanysService;
import com.tsingshan.system.service.IJobService;
import com.tsingshan.system.service.ISysPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.service.IEmployeeService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

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
	public TableDataInfo list(Employee employee)
	{
		startPage();
        List<Employee> list = employeeService.selectEmployeeList(employee);
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
		Employee employee = employeeService.selectEmployeeById(employeeId);
		mmap.put("employee", employee);
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
		List<Employee> list = employeeService.selectExport(ids);
		ExcelUtil<Employee> util = new ExcelUtil<Employee>(Employee.class);
		return util.exportExcel(list, "employee");
	}

    /**
     * 导入员工
     */
    @GetMapping("/uploadExcel")
    public String uploadExcel()
    {
        return prefix + "/uploadExcel";
    }

	@Log(title = "员工", businessType = BusinessType.IMPORT)
    @RequiresPermissions("roster:employee:import")
    @PostMapping("/uploadExcel/import")
    @ResponseBody
    public String employeeImport(@RequestParam(value = "upfile")MultipartFile file)
    {
        try {
//            List<List<Object>> listob = null;
            if(file.isEmpty()){
                throw new Exception("文件不存在！");
            }
            InputStream in = file.getInputStream();
            String fileName = file.getOriginalFilename();
            ExcelUtil<Employee> util = new ExcelUtil<Employee>(Employee.class);
            List<Employee> list = util.importExcel(fileName,in);
//            for (Employee emp : list) {
//                System.out.println(emp);
//            }
            employeeService.batchInsertEmployee(list);
            return "success";
        } catch (Exception e) {
            LOGGER.error("导入异常{}", e.getMessage());
            return "error";
        }


    }

    @Log(title = "根据ID查询员工信息", businessType = BusinessType.OTHER)
    @PostMapping("/employeeId")
    @ResponseBody
    public AjaxResult findByEmployeeNo(Long employeeId) {
        Employee employee = employeeService.selectEmployeeById(employeeId);
        return AjaxResult.success(employee);
    }


	@Override
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
