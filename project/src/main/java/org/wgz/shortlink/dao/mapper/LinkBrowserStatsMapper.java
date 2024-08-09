package org.wgz.shortlink.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wgz.shortlink.dao.entity.LinkBrowserStatsDO;
import org.wgz.shortlink.dto.req.ShortLinkStatsReqDTO;

import java.util.HashMap;
import java.util.List;

/**
 * @author 下水道的小老鼠
 * &#064;description  针对表【t_link_browser_stats】的数据库操作Mapper
 * &#064;createDate  2024-08-09 12:26:01
 * &#064;Entity  org.wgz.shortlink.dao.entity.TLinkBrowserStats
 */
public interface LinkBrowserStatsMapper extends BaseMapper<LinkBrowserStatsDO> {

    /**
     * 记录浏览器访问监控数据
     */
    @Insert("INSERT INTO t_link_browser_stats (full_short_url, gid, date, cnt, browser, create_time, update_time) " +
            "VALUES( #{linkBrowserStatsDO.fullShortUrl}, #{linkBrowserStatsDO.gid}, #{linkBrowserStatsDO.date}, #{linkBrowserStatsDO.cnt}, #{linkBrowserStatsDO.browser}, NOW(), NOW()) " +
            "ON DUPLICATE KEY UPDATE cnt = cnt +  #{linkBrowserStatsDO.cnt};")
    void shortLinkBrowserState(@Param("linkBrowserStatsDO") LinkBrowserStatsDO linkBrowserStatsDO);

    /**
     * 根据短链接获取指定日期内浏览器监控数据
     */
    @Select("SELECT " +
            "    browser, " +
            "    SUM(cnt) AS count " +
            "FROM " +
            "    t_link_browser_stats " +
            "WHERE " +
            "    full_short_url = #{param.fullShortUrl} " +
            "    AND gid = #{param.gid} " +
            "    AND date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    full_short_url, gid, date, browser;")
    List<HashMap<String, Object>> listBrowserStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);
}




