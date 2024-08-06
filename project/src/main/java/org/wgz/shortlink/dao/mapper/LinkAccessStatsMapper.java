package org.wgz.shortlink.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.wgz.shortlink.dao.entity.LinkAccessStatsDO;

/**
 * @author 下水道的小老鼠
 * @description 针对表【t_link_access_stats】的数据库操作Mapper
 * @createDate 2024-08-06 13:15:01
 * @Entity generator.domain.TLinkAccessStats
 */
public interface LinkAccessStatsMapper extends BaseMapper<LinkAccessStatsDO> {

    @Insert("INSERT INTO t_link_access_stats (" +
            "full_short_url, gid, date, pv, uv, uip, hour, weekday, create_time, update_time, del_flag) " +
            "VALUES (" +
            "#{fullShortUrl}, #{gid}, #{date}, #{pv}, #{uv}, #{uip}, #{hour}, #{weekday}, NOW(), NOW(), #{delFlag}) " +
            "ON DUPLICATE KEY UPDATE " +
            "pv = pv + VALUES(pv), " +
            "uv = uv + VALUES(uv), " +
            "uip = uip + VALUES(uip)")
    void insertOrUpdateToStats(@Param("linkAccessStats") LinkAccessStatsDO linkAccessStats);
}




