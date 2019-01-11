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
import com.tsingshan.system.domain.Companys;
import com.tsingshan.system.domain.SysPost;
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
import com.tsingshan.infomana.domain.Transaction;
import com.tsingshan.infomana.service.ITransactionService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;

/**
 * 异动 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Controller
@RequestMapping("/roster/transaction")
public class TransactionController extends BaseController
{
    private String prefix = "roster/transaction";
	
	@Autowired
	private ITransactionService transactionService;

	@Autowired
	private IEmployeeService employeeService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private IJobService jobService;

    @Autowired
    private ICompanysService companysService;
	
	@RequiresPermissions("roster:transaction:view")
	@GetMapping()
	public String transaction()
	{
	    return prefix + "/transaction";
	}
	
	/**
	 * 查询异动列表
	 */
	@RequiresPermissions("roster:transaction:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Transaction transaction)
	{
		startPage();
        List<Transaction> list = transactionService.selectTransactionList(transaction);
		return getDataTable(list);
	}
	
	/**
	 * 新增异动
	 */
	@GetMapping("/add")
	public String add(ModelMap modelMap)
	{
	    modelMap.put("companys", companysService.selectCompanyAll());
        modelMap.put("posts", postService.selectPostAll());
        modelMap.put("jobs", jobService.selectJobAll());
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存异动
	 */
	@RequiresPermissions("roster:transaction:add")
	@Log(title = "异动", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Transaction transaction,Long deptId)
	{
	    if (StringUtils.isEmpty(transaction.getEmployeeNo())) {
	        return AjaxResult.error("工号不能为空");
        } else if (StringUtils.isEmpty(transaction.getTransInCompany())) {
	        return AjaxResult.error("异动入公司不能为空");
        } else if (StringUtils.isEmpty(transaction.getTransInDept())) {
	        return AjaxResult.error("异动入部门不能为空");
        } else if (StringUtils.isEmpty(transaction.getTransInPost())) {
	        return AjaxResult.error("异动入岗位不能为空");
        }
//        Companys companys = companysService.selectCompanysByCompanyCode(transaction.getTransInCompany());
//        SysPost sysPost = postService.selectPostByPostName(transaction.getTransInPost());
        //判断异动入公司 是否与 异动出公司相等  不相等就是跨公司异动
        if (!transaction.getTransInCompany().equals(transaction.getTransOutCompany())) {
            //设置 是否跨公司异动   0 是 1 否
            transaction.setSpanCompany("是");
        } else {
            transaction.setSpanCompany("否");
        }
        transaction.setCreateBy(ShiroUtils.getLoginName());
        transactionService.insertTransaction(transaction);
        updateEmployee(transaction.getEmployeeId(),transaction.getTransInCompany(), transaction.getTransInDept(), transaction.getTransInPost());
        return AjaxResult.success();
	}
    private void updateEmployee (long employeeId, String companyCode, String deptName, String postName) {
        Employee employee = employeeService.selectEmployeeById(employeeId);
        employee.setCompanyCode(companyCode);
        employee.setDeptName(deptName);
        employee.setPostName(postName);
        employeeService.updateEmployee(employee);
    }

	/**
	 * 修改异动
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Transaction transaction = transactionService.selectTransactionById(id);
		mmap.put("transaction", transaction);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存异动
	 */
	@RequiresPermissions("roster:transaction:edit")
	@Log(title = "异动", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Transaction transaction)
	{
		transaction.setUpdateBy(ShiroUtils.getLoginName());
        AjaxResult ajaxResult = transactionService.updateTransaction(transaction);
        return ajaxResult;
	}

	@Log(title = "异动", businessType = BusinessType.EXPORT)
	@RequiresPermissions("roster:transaction:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(String  ids)
	{
		List<Transaction> list = transactionService.selectExport(ids);
		ExcelUtil<Transaction> util = new ExcelUtil<Transaction>(Transaction.class);
        AjaxResult ajaxResult = util.exportExcel(list, "transaction");
        return ajaxResult;
	}
	
	/**
	 * 删除异动
	 */
	@RequiresPermissions("roster:transaction:remove")
	@Log(title = "异动", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
        AjaxResult ajaxResult = transactionService.deleteTransactionByIds(ids);
        return ajaxResult;
    }

	/**
	 * 向页面put值
	 * @param employeeId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/{employeeId}")
	public String transaByEmpId(@PathVariable("employeeId") Long employeeId, ModelMap mmap)
	{
		mmap.put("employeeId", employeeId);
		return prefix + "/transaction";
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
//        map.put("employee", employee);
////		map.put("employeeId", employee.getEmployeeId());
////		map.put("employeeName", employee.getEmployeeName());
////		map.put("employeeNo", employee.getEmployeeNo());
////		map.put("identityCard", employee.getIdentityCard());
//		return prefix + "/add2";
//	}

	@Override
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
