package com.tsingshan.web.controller.roster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsingshan.common.annotation.Log;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.enums.BusinessType;
import com.tsingshan.framework.util.ShiroUtils;
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
import com.tsingshan.infomana.domain.LevelGrade;
import com.tsingshan.infomana.service.ILevelGradeService;
import com.tsingshan.web.core.base.BaseController;
import com.tsingshan.framework.web.page.TableDataInfo;


/**
 * 薪资级档 信息操作处理
 * 
 * @author tsingshan
 * @date 2018-12-30
 */
@Controller
@RequestMapping("/roster/levelGrade")
public class LevelGradeController extends BaseController
{
    private String prefix = "roster/levelGrade";
	
	@Autowired
	private ILevelGradeService levelGradeService;
	
	@RequiresPermissions("roster:levelGrade:view")
	@GetMapping()
	public String levelGrade()
	{
	    return prefix + "/levelGrade";
	}
	
	/**
	 * 查询薪资级档列表
	 */
	@RequiresPermissions("roster:levelGrade:list")
	@GetMapping("/list")
	@ResponseBody
	public List<LevelGrade> list(LevelGrade levelGrade)
	{
        List<LevelGrade> list = levelGradeService.selectLevelGradeList(levelGrade);
		return list;
	}
	
	/**
	 * 新增薪资级档
	 */
	@GetMapping("/add/{parentId}")
	public String add(@PathVariable("parentId") Integer parentId, ModelMap mmap)
	{
        LevelGrade levelGrade = null;
	    if (0 != parentId) {
            levelGrade = levelGradeService.selectLevelGradeById(parentId);
        } else {
	        levelGrade = new LevelGrade();
	        levelGrade.setId(0);
	        levelGrade.setLevelGradeName("主级别");
        }
        mmap.put("levelGrade", levelGrade);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存薪资级档
	 */
	@RequiresPermissions("roster:levelGrade:add")
	@Log(title = "薪资级档", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(LevelGrade levelGrade)
	{
	    levelGrade.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(levelGradeService.insertLevelGrade(levelGrade));
	}

	/**
	 * 修改薪资级档
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		LevelGrade levelGrade = levelGradeService.selectLevelGradeById(id);
		mmap.put("levelGrade", levelGrade);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存薪资级档
	 */
	@RequiresPermissions("roster:levelGrade:edit")
	@Log(title = "薪资级档", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(LevelGrade levelGrade)
	{
	    levelGrade.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(levelGradeService.updateLevelGrade(levelGrade));
	}
	
	/**
	 * 删除薪资级档
	 */
	@RequiresPermissions("roster:levelGrade:remove")
	@Log(title = "薪资级档", businessType = BusinessType.DELETE)
	@PostMapping( "/remove/{id}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("id") Integer id)
	{
	    if (levelGradeService.selectCountLevelGradeByParentId(id) > 0) {
	        return error(1,"存在子级档，不允许删除");
        }
		return toAjax(levelGradeService.deleteLevelGradeById(id));
	}


    /**
     * 选择级档树
     */
    @GetMapping("/selectLevelGradeTree/{id}")
    public String selectLevelGradeTree(@PathVariable("id") Integer id, ModelMap mmap)
    {
        mmap.put("levelGrade", levelGradeService.selectLevelGradeById(id));
        return prefix + "/tree";
    }

    /**
     * 加载所有级档列表树
     */
    @GetMapping("/levelGradeTreeData")
    @ResponseBody
    public List<Map<String, Object>> levelGradeTreeData()
    {
        List<Map<String, Object>> tree = levelGradeService.levelGradeTreeData();
        return tree;
    }

    /**
     * 选择级档图标
     */
    @GetMapping("/icon")
    public String icon()
    {
        return prefix + "/icon";
    }

    @Override
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
