package org.wgz.shortlink.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.wgz.shortlink.dao.entity.LinkStatsTodayDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 下水道的小老鼠
 * &#064;description  针对表【t_link_stats_today_0】的数据库操作Mapper
 * &#064;createDate  2024-08-10 13:35:22
 * &#064;Entity  generator.domain.LinkStatsToday
 */
public interface LinkStatsTodayMapper extends BaseMapper<LinkStatsTodayDO> {

    @Insert("INSERT INTO t_link_stats_today (gid,full_short_url,date,today_pv,today_uv,today_uip,create_time,update_time)" +
            "VALUES (#{linkStatsTodayDO.gid}," +
            "#{linkStatsTodayDO.fullShortUrl}," +
            "#{linkStatsTodayDO.date}," +
            "#{linkStatsTodayDO.todayPv}," +
            "#{linkStatsTodayDO.todayUv}," +
            "#{linkStatsTodayDO.todayUip}," +
            "NOW(),NOW())" +
            "ON DUPLICATE KEY UPDATE today_uv = today_uv + #{linkStatsTodayDO.todayUv}," +
            "today_pv = today_pv + #{linkStatsTodayDO.todayPv}," +
            "today_uip = today_uip + #{linkStatsTodayDO.todayUip}")
    void shortLinkTodayState(@Param("linkStatsTodayDO") LinkStatsTodayDO linkStatsTodayDO);
}




