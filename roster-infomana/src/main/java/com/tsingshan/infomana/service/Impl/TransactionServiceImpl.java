package com.tsingshan.infomana.service.Impl;

import java.util.List;

import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.support.Convert;
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.domain.vo.EmployeeVo;
import com.tsingshan.infomana.mapper.EmployeeMapper;
import com.tsingshan.infomana.mapper.EmployeeVoMapper;
import com.tsingshan.infomana.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsingshan.infomana.mapper.TransactionMapper;
import com.tsingshan.infomana.domain.Transaction;
import com.tsingshan.infomana.service.ITransactionService;


/**
 * 异动 服务层实现
 * 
 * @author tsingshan
 * @date 2018-10-31
 */
@Service
public class TransactionServiceImpl implements ITransactionService 
{
	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private TransactionMapper transactionMapper;

	@Autowired
   private EmployeeMapper employeeMapper;



	/**
     * 查询异动信息
     * 
     * @param id 异动ID
     * @return 异动信息
     */
    @Override
	public Transaction selectTransactionById(long id)
	{
	    return transactionMapper.selectTransactionById(id);
	}
	
	/**
     * 查询异动列表
     * 
     * @param transaction 异动信息
     * @return 异动集合
     */
	@Override
	public List<Transaction> selectTransactionList(Transaction transaction)
	{
	    return transactionMapper.selectTransactionList(transaction);
	}
	
    /**
     * 新增异动
     * 
     * @param transaction 异动信息
     * @return 结果
     */
	@Override
	public AjaxResult insertTransaction(Transaction transaction,long deptId,long postId)
	{
		try {
            //判断当前异动入部门是否为空
            if (null != transaction.getTransInDept() || !"".equals(transaction.getTransInDept())) {
                //判断异动入部门是否与异动出部门相等
                if (transaction.getTransOutDept().equals(transaction.getTransInDept())) {
                    return AjaxResult.error("异动入部门与异动出部门相等");
                }
                transactionMapper.insertTransaction(transaction);
                updateEmployee(transaction.getEmployeeId(), deptId, postId);
                return AjaxResult.success();
            }
            return AjaxResult.error("数据异常");
        } catch (Exception e) {
		    logger.error("插入异动信息异常{}", e.getMessage());
		    return AjaxResult.error("添加异常");
        }
	}
	private void updateEmployee (long employeeId, long deptId, long postId) {
        Employee employee = employeeMapper.selectEmployeeById(employeeId);
        employee.setDeptId(deptId);
        employee.setPostId(postId);
        employeeMapper.updateEmployee(employee);
    }
	
	/**
     * 修改异动
     * 
     * @param transaction 异动信息
     * @return 结果
     */
	@Override
	public AjaxResult updateTransaction(Transaction transaction)
	{
	    try {
            int i = transactionMapper.updateTransaction(transaction);
            return AjaxResult.toAjax(i);
        } catch (Exception e) {
	        logger.error("更新异动信息异常{}", e.getMessage());
	        return AjaxResult.error("更新异常");
        }
	}

	/**
     * 删除异动对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public AjaxResult deleteTransactionByIds(String ids)
	{
	    try {
            int i = transactionMapper.deleteTransactionByIds(Convert.toStrArray(ids));
            return AjaxResult.toAjax(i);
        } catch (Exception e) {
	        logger.error("删除异动信息异常{}", e.getMessage());
	        return AjaxResult.error("删除异常");
        }
	}

	@Override
	public List<Transaction> selectExport(String ids) {
        return transactionMapper.selectExport(Convert.toLongArray(ids));
	}

}
