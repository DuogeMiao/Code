package com.tsingshan.system.mapper;

import java.util.List;
import com.tsingshan.system.domain.SysLogininfor;
import org.apache.ibatis.annotations.Param;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author 拖板孩
 */
public interface SysLogininforMapper
{
    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    public int deleteLogininforByIds(String[] ids);

    /**
     * 清空系统登录日志
     */
    public int cleanLogininfor();


    /**
     * 根据id查询显示任意列表
     * @param ids
     * @return
     */
    List<SysLogininfor> selectExport(@Param("ids") String[] ids);
}
