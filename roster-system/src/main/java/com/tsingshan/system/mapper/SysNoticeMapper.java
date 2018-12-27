package com.tsingshan.system.mapper;

import java.util.List;
import com.tsingshan.system.domain.SysNotice;
import org.apache.ibatis.annotations.Param;

/**
 * 公告 数据层
 * 
 * @author 拖板孩
 */
public interface SysNoticeMapper
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice);

    /**
     * 批量删除公告
     * 
     * @param noticeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteNoticeByIds(String[] noticeIds);

    /**
     * 根据id查询显示任意列表
     * @param params
     * @param ids
     * @return
     */
    public List<SysNotice> selectExport(@Param("array") String[] params, @Param("ids") Integer[] ids);
}