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
import com.tsingshan.system.domain.Job;
import com.tsingshan.system.domain.SysDept;
import com.tsingshan.system.domain.SysPost;
import com.tsingshan.system.service.IJobService;
import com.tsingshan.system.service.ISysDeptService;
import com.tsingshan.system.service.ISysPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tsingshan.infomana.domain.Promotion;
import com.tsingshan.infomana.service.IPromotionService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;

/**
 * 晋升 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Controller
@RequestMapping("/roster/promotion")
public class PromotionController extends BaseController
{
    private static Logger logger = LoggerFactory.getLogger(PromotionController.class);

    private String prefix = "roster/promotion";
	
	@Autowired
	private IPromotionService promotionService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private ISysPostService postService;

	@Autowired
	private IJobService jobService;

	
	@RequiresPermissions("roster:promotion:view")
	@GetMapping()
	public String promotion()
	{
	    return prefix + "/promotion";
	}
	
	/**
	 * 查询晋升列表
	 */
	@RequiresPermissions("roster:promotion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Promotion promotion)
	{
		startPage();
        List<Promotion> list = promotionService.selectPromotionList(promotion);
		return getDataTable(list);
	}
	
	/**
	 * 新增晋升
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("posts", postService.selectPostAll());
		mmap.put("jobs", jobService.selectJobAll());
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存晋升
	 */
	@RequiresPermissions("roster:promotion:add")
	@Log(title = "晋升", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Promotion promotion, Long postId, Long jobId)
	{
        try {
            if (postId == null || jobId == null) {
                return AjaxResult.error("岗位和职务不能为空");
            }
            SysPost sysPost = postService.selectPostById(postId);
            Job job = jobService.selectJobById(jobId);
            if (promotion.getJobName().equals(job.getJobName())) {
                return AjaxResult.error("职务必须改变");
            }
            promotion.setPostName(sysPost.getPostName());
            promotion.setJobName(job.getJobName());
            promotion.setCreateBy(ShiroUtils.getLoginName());
            promotionService.insertPromotion(promotion);
            //更新员工岗位或者职务
            updateEmployee(promotion.getEmployeeId(), postId, jobId);
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("新增晋升信息异常{}", e.getMessage());
            return AjaxResult.error();
        }
	}

    private void updateEmployee(long employeeId, long postId, long jobId) {
        Employee employee = employeeService.selectEmployeeById(employeeId);
        employee.setPostId(postId);
        employee.setJobId(jobId);
        employeeService.updateEmployee(employee);
    }

	/**
	 * 修改晋升
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Promotion promotion = promotionService.selectPromotionById(id);
		mmap.put("promotion", promotion);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存晋升
	 */
	@RequiresPermissions("roster:promotion:edit")
	@Log(title = "晋升", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Promotion promotion)
	{
		promotion.setUpdateBy(ShiroUtils.getLoginName());
		AjaxResult ajaxResult = promotionService.updatePromotion(promotion);
		return ajaxResult;
	}
	
	/**
	 * 删除晋升
	 */
	@RequiresPermissions("roster:promotion:remove")
	@Log(title = "晋升", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		AjaxResult ajaxResult = promotionService.deletePromotionByIds(ids);
		return ajaxResult;

	}

	@Log(title = "晋升", businessType = BusinessType.EXPORT)
	@RequiresPermissions("roster:promotion:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(String  ids)
	{
		List<Promotion> list = promotionService.selectExport(ids);
		ExcelUtil<Promotion> util = new ExcelUtil<Promotion>(Promotion.class);
		return util.exportExcel(list, "promotion");
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
		return prefix + "/promotion";
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

	@Override
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
