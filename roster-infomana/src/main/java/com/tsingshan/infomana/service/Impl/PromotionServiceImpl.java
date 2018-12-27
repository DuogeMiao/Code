package com.tsingshan.infomana.service.Impl;

import java.util.List;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.PromotionMapper;
import com.tsingshan.infomana.domain.Promotion;
import com.tsingshan.infomana.service.IPromotionService;


/**
 * 晋升 服务层实现
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Service
public class PromotionServiceImpl implements IPromotionService 
{
	private static final Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

	@Autowired
	private PromotionMapper promotionMapper;

	@Autowired
    private EmployeeMapper employeeMapper;


	/**
     * 查询晋升信息
     * 
     * @param id 晋升ID
     * @return 晋升信息
     */
    @Override
	public Promotion selectPromotionById(long id)
	{
	    return promotionMapper.selectPromotionById(id);
	}
	
	/**
     * 查询晋升列表
     * 
     * @param promotion 晋升信息
     * @return 晋升集合
     */
	@Override
	public List<Promotion> selectPromotionList(Promotion promotion)
	{
	    return promotionMapper.selectPromotionList(promotion);
	}
	
    /**
     * 新增晋升
     * 
     * @param promotion 晋升信息
     * @return 结果
     */
	@Override
	public AjaxResult insertPromotion(Promotion promotion,  long postId, long jobId)
	{
		try {
			promotionMapper.insertPromotion(promotion);
			//更新员工岗位或者职务
			updateEmployee(promotion.getEmployeeId(), postId, jobId);
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("新增晋升信息异常{}", e.getMessage());
			return AjaxResult.error("新增异常");
		}
	}

	private void updateEmployee(long employeeId, long postId, long jobId) {
        Employee employee = employeeMapper.selectEmployeeById(employeeId);
        employee.setPostId(postId);
        employee.setJobId(jobId);
        employeeMapper.updateEmployee(employee);
    }
	
	/**
     * 修改晋升
     * 
     * @param promotion 晋升信息
     * @return 结果
     */
	@Override
	public AjaxResult updatePromotion(Promotion promotion)
	{
		try {

			promotionMapper.updatePromotion(promotion);
			return AjaxResult.success();
		} catch (Exception e) {
			logger.error("更新晋升信息异常{}", e.getMessage());
			return AjaxResult.error("修改异常");
		}
	}

	/**
     * 删除晋升对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deletePromotionByIds(String ids)
	{
        try {
            promotionMapper.deletePromotionByIds(Convert.toLongArray(ids));
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error("根据ID删除晋升信息异常{}", e.getMessage());
            return AjaxResult.error("删除异常");
        }
    }

	@Override
	public List<Promotion> selectExport(String ids) {
		return promotionMapper.selectExport(Convert.toLongArray(ids));
	}

}
