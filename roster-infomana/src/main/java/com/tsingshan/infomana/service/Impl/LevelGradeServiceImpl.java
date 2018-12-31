package com.tsingshan.infomana.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsingshan.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.LevelGradeMapper;
import com.tsingshan.infomana.domain.LevelGrade;
import com.tsingshan.infomana.service.ILevelGradeService;

/**
 * 薪资级档 服务层实现
 * 
 * @author tsingshan
 * @date 2018-12-30
 */
@Service
public class LevelGradeServiceImpl implements ILevelGradeService 
{
	@Autowired
	private LevelGradeMapper levelGradeMapper;

	/**
     * 查询薪资级档信息
     * 
     * @param id 薪资级档ID
     * @return 薪资级档信息
     */
    @Override
	public LevelGrade selectLevelGradeById(Integer id)
	{
	    return levelGradeMapper.selectLevelGradeById(id);
	}
	
	/**
     * 查询薪资级档列表
     * 
     * @param levelGrade 薪资级档信息
     * @return 薪资级档集合
     */
	@Override
	public List<LevelGrade> selectLevelGradeList(LevelGrade levelGrade)
	{
	    return levelGradeMapper.selectLevelGradeList(levelGrade);
	}
	
    /**
     * 新增薪资级档
     * 
     * @param levelGrade 薪资级档信息
     * @return 结果
     */
	@Override
	public int insertLevelGrade(LevelGrade levelGrade)
	{
	    return levelGradeMapper.insertLevelGrade(levelGrade);
	}
	
	/**
     * 修改薪资级档
     * 
     * @param levelGrade 薪资级档信息
     * @return 结果
     */
	@Override
	public int updateLevelGrade(LevelGrade levelGrade)
	{
	    return levelGradeMapper.updateLevelGrade(levelGrade);
	}

	/**
     * 删除薪资级档对象
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteLevelGradeById(Integer id)
	{
		return levelGradeMapper.deleteLevelGradeById(id);
	}

    /**
     * 查询所有级档
     * @return 级档列表
     */
    @Override
    public List<Map<String, Object>> levelGradeTreeData() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<LevelGrade> levelGradeList = levelGradeMapper.selectLevelGradeAll();
        trees = getTrees(levelGradeList,false);
        return trees;
    }

    @Override
    public int selectCountLevelGradeByParentId(Integer parentId) {
        return levelGradeMapper.selectCountLevelGradeByParentId(parentId);
    }

    /**
     * 对象转换级档树
     * @param levelGradeList 级档列表
     * @param isCheck 是否需要选中
     * @return
     */
    private List<Map<String, Object>> getTrees(List<LevelGrade> levelGradeList, boolean isCheck) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (LevelGrade levelGrade : levelGradeList) {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", levelGrade.getId());
            deptMap.put("pId", levelGrade.getParentId());
            deptMap.put("name", levelGrade.getLevelGradeName());
            deptMap.put("baseSalary", levelGrade.getBaseSalary());
            deptMap.put("jobSalary", levelGrade.getJobSalary());
            deptMap.put("everyGradeChange", levelGrade.getEveryGradeChange());
            deptMap.put("jobName", levelGrade.getJobName());
            if (isCheck) {
                deptMap.put("checked", true);
            } else {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }

}
