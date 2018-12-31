package com.tsingshan.infomana.service;

import com.tsingshan.infomana.domain.LevelGrade;
import java.util.List;
import java.util.Map;

/**
 * 薪资级档 服务层
 * 
 * @author tsingshan
 * @date 2018-12-30
 */
public interface ILevelGradeService 
{
	/**
     * 查询薪资级档信息
     * 
     * @param id 薪资级档ID
     * @return 薪资级档信息
     */
	LevelGrade selectLevelGradeById(Integer id);
	
	/**
     * 查询薪资级档列表
     * 
     * @param levelGrade 薪资级档信息
     * @return 薪资级档集合
     */
	List<LevelGrade> selectLevelGradeList(LevelGrade levelGrade);
	
	/**
     * 新增薪资级档
     * 
     * @param levelGrade 薪资级档信息
     * @return 结果
     */
	int insertLevelGrade(LevelGrade levelGrade);
	
	/**
     * 修改薪资级档
     * 
     * @param levelGrade 薪资级档信息
     * @return 结果
     */
	int updateLevelGrade(LevelGrade levelGrade);
		
	/**
     * 删除薪资级档信息
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
	int deleteLevelGradeById(Integer id);

    /**
     * 查询所有菜单信息
     *
     * @return 菜单列表
     */
    List<Map<String,Object>> levelGradeTreeData();

    /**
     * 查询级档数量
     *
     * @param parentId 级档父ID
     * @return 结果
     */
    int selectCountLevelGradeByParentId(Integer parentId);
}
