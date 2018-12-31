package com.tsingshan.infomana.mapper;

import com.tsingshan.infomana.domain.LevelGrade;
import java.util.List;	

/**
 * 薪资级档 数据层
 * 
 * @author tsingshan
 * @date 2018-12-30
 */
public interface LevelGradeMapper 
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
     * 删除薪资级档
     * 
     * @param id 薪资级档ID
     * @return 结果
     */
	int deleteLevelGradeById(Integer id);
	
	/**
     * 批量删除薪资级档
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteLevelGradeByIds(Integer[] ids);

    /**
     * 查询所有级档（含按钮）
     *
     * @return 菜单列表
     */
    List<LevelGrade> selectLevelGradeAll();

    /**
     * 查询级档数量
     *
     * @param parentId 级档父ID
     * @return 结果
     */
    int selectCountLevelGradeByParentId(Integer parentId);
}