package org.wgz.shortlink.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.wgz.shortlink.dao.entity.LinkBrowserStatsDO;

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
            "VALUES( #{linkBrowserStats.fullShortUrl}, #{linkBrowserStats.gid}, #{linkBrowserStats.date}, #{linkBrowserStats.cnt}, #{linkBrowserStats.browser}, NOW(), NOW()) " +
            "ON DUPLICATE KEY UPDATE cnt = cnt +  #{linkBrowserStats.cnt};")
    void shortLinkBrowserState(@Param("linkBrowserStatsDO") LinkBrowserStatsDO linkBrowserStatsDO);
}




