package org.wgz.shortlink.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.wgz.shortlink.dao.entity.ShortLinkDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 下水道的小老鼠
 * @description 针对表【t_link】的数据库操作Mapper
 * @createDate 2024-08-02 11:22:12
 * @Entity generator.domain.TLink
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {

    @Update("update t_link set " +
            "total_uv = total_uv + #{totalUv}," +
            "total_pv = total_pv + #{totalPV}," +
            "total_uip = total_uip + #{totalUip}" +
            "when gid = #{gid} and full_short_url = #{fullShortUrl}")
    void incrementStats(
            @Param("gid") String gid,
            @Param("fullShortUrl") String fullShortUrl,
            @Param("totalUv") Integer totalUv,
            @Param("totalPv") Integer totalPv,
            @Param("totalUip") Integer totalUip
    );
}




