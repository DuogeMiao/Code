package com.tsingshan.web.controller.roster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tsingshan.common.annotation.Log;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.enums.BusinessType;
import com.tsingshan.common.utils.DateUtils;
import com.tsingshan.common.utils.ExcelUtil;
import com.tsingshan.common.utils.StringUtils;
import com.tsingshan.framework.util.ShiroUtils;
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.domain.vo.ContractVo;
import com.tsingshan.infomana.domain.vo.EmployeeVo;
import com.tsingshan.infomana.service.IEmployeeService;
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
import com.tsingshan.infomana.domain.Contract;
import com.tsingshan.infomana.service.IContractService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;


/**
 * 合同 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Controller
@RequestMapping("/roster/contract")

public class ContractController extends BaseController
{
    private String prefix = "roster/contract";
	
	@Autowired
	private IContractService contractService;

	@Autowired
    private IEmployeeService employeeService;

	@Autowired
	private ISysPostService postService;

	@Autowired
	private IJobService jobService;

	@Autowired
	private ICompanysService companysService;

	@RequiresPermissions("roster:contract:view")
	@GetMapping()
	public String contract() {
		return prefix + "/contract";
	}
	
	/**
	 * 查询合同列表
	 */
	@RequiresPermissions("roster:contract:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Contract contract)
	{
		startPage();
		List<Contract> list = contractService.selectContractList(contract);
		return getDataTable(list);
	}
	
	/**
	 * 新增合同
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	/**
	 * 根据员工id回显员工信息到页面自动填充
	 * @param employeeId
	 * @param map
	 * @return
	 */
	@GetMapping("/add/{employeeId}")
	public String addByEmployeeId(@PathVariable("employeeId") String employeeId, ModelMap map) {
		EmployeeVo employeeVo = employeeService.selectEmployeeVoById(Long.valueOf(employeeId));
		map.put("employeeVo", employeeVo);
		map.put("companys", companysService.selectCompanyAll());
		map.put("jobs", jobService.selectJobAll());
		map.put("posts", postService.selectPostAll());
		return prefix + "/add2";
	}

	/**
	 * 新增保存合同
	 *
	 */
	@RequiresPermissions("roster:contract:add")
	@Log(title = "合同", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Contract contract)
	{
		contract.setCreateBy(ShiroUtils.getLoginName());
		AjaxResult result = contractService.insertContract(contract);
		return result;
	}

	/**
	 * 修改合同
	 */
	@GetMapping("/edit/{contractId}")
	public String edit(@PathVariable("contractId") Long contractId, ModelMap modelMap)
	{
		Contract contract = contractService.selectContractById(contractId);
        modelMap.put("contract", contract);
	    return prefix + "/edit";
	}
	/**
	 * 修改保存合同
	 */
	@RequiresPermissions("roster:contract:edit")
	@Log(title = "合同", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Contract contract)
	{
		contract.setUpdateBy(ShiroUtils.getLoginName());
        AjaxResult ajaxResult = contractService.updateContract(contract);
        return ajaxResult;
	}

	/**
	 * 重签合同
	 * @param contractId
	 * @param modelMap
	 * @return
	 */
	@GetMapping("/reSign/{contractId}")
	public String reSignContract(@PathVariable("contractId") Long contractId, ModelMap modelMap) {
		Contract contract = contractService.selectContractById(contractId);
        modelMap.put("contract", contract);
		return prefix + "/reSignContract";
	}

	@Log(title = "重签合同", businessType = BusinessType.INSERT)
	@PostMapping("/reSign")
	@ResponseBody
	public AjaxResult reSignContractSave(Contract contract) {
	    if (StringUtils.isEmpty(contract.getContractNo())) {
	        return AjaxResult.error("合同号不能为空");
        }
		contract.setCreateBy(ShiroUtils.getLoginName());
        AjaxResult ajaxResult = contractService.reSignContract(contract);
        return ajaxResult;
	}
	
	/**
	 * 删除合同
	 */
	@RequiresPermissions("roster:contract:remove")
	@Log(title = "合同", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
        AjaxResult ajaxResult = contractService.deleteContractByIds(ids);
        return ajaxResult;
    }

	@Log(title = "合同导出", businessType = BusinessType.EXPORT)
	@RequiresPermissions("roster:contract:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(String  ids)
	{
		List<Contract> list = contractService.selectExport(ids);
		ExcelUtil<Contract> util = new ExcelUtil<Contract>(Contract.class);
		return util.exportExcel(list, "contract");
	}


    /**
     * 向页面put值
     * @param employeeId
     * @param modelMap
     * @return
     */
	@GetMapping("/{employeeId}")
	public String contractByEmpId(@PathVariable("employeeId") Long employeeId, ModelMap modelMap)
	{
        modelMap.put("employeeId", employeeId);
		return prefix + "/contract";
	}

	@Override
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
