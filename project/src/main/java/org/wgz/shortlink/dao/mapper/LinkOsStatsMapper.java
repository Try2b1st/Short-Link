package org.wgz.shortlink.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wgz.shortlink.dao.entity.LinkOsStatsDO;
import org.wgz.shortlink.dto.req.ShortLinkStatsReqDTO;

import java.util.HashMap;
import java.util.List;

/**
 * @author 下水道的小老鼠
 * &#064;description  针对表【t_link_os_stats(短链接监控操作系统访问状态)】的数据库操作Mapper
 * &#064;createDate  2024-08-09 11:54:06
 * &#064;Entity  generator.domain.TLinkOsStats
 */
public interface LinkOsStatsMapper extends BaseMapper<LinkOsStatsDO> {
    @Insert("INSERT INTO t_link_os_stats (" +
            "full_short_url, gid, date, cnt, os, create_time, update_time) " +
            "VALUES (" +
            "#{linkOsStats.fullShortUrl}, #{linkOsStats.gid}, #{linkOsStats.date}, #{linkOsStats.cnt}, #{linkOsStats.os}, NOW(), NOW()) " +
            "ON DUPLICATE KEY UPDATE " +
            "cnt = cnt + VALUES(cnt)")
    void upsertLinkOsStats(@Param("linkOsStats") LinkOsStatsDO linkOsStats);

    /**
     * 根据短链接获取指定日期内操作系统监控数据
     */
    @Select("SELECT " +
            "    os, " +
            "    SUM(cnt) AS count " +
            "FROM " +
            "    t_link_os_stats " +
            "WHERE " +
            "    full_short_url = #{param.fullShortUrl} " +
            "    AND gid = #{param.gid} " +
            "    AND date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    full_short_url, gid, os;")
    List<HashMap<String, Object>> listOsStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);
}




