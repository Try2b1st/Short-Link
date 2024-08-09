package org.wgz.shortlink.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.wgz.shortlink.dao.entity.LinkLocaleStatsDO;

/**
 * @author 下水道的小老鼠
 * @description 针对表【t_link_locale_stats】的数据库操作Mapper
 * @createDate 2024-08-06 16:02:35
 * @Entity generator.domain.TLinkLocaleStats
 */
public interface LinkLocaleStatsMapper extends BaseMapper<LinkLocaleStatsDO> {

    @Insert("INSERT INTO t_link_locale_stats (" +
            "full_short_url, gid, date, cnt, province, city, adcode, country, create_time, update_time) " +
            "VALUES (" +
            "#{linkLocaleStatsDO.fullShortUrl}, " +
            "#{linkLocaleStatsDO.gid}, " +
            "#{linkLocaleStatsDO.date}, " +
            "#{linkLocaleStatsDO.cnt}," +
            "#{linkLocaleStatsDO.province}, " +
            "#{linkLocaleStatsDO.city}, " +
            "#{linkLocaleStatsDO.adcode}, " +
            "#{linkLocaleStatsDO.country}, " +
            "NOW(), " +
            "NOW()) " +
            "ON DUPLICATE KEY UPDATE " +
            "cnt = cnt + VALUES(cnt)")
    void shortLinkLocalStats(@Param("linkLocaleStatsDO") LinkLocaleStatsDO linkLocaleStatsDO);

}




