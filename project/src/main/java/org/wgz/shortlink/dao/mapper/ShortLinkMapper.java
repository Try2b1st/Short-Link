package org.wgz.shortlink.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.wgz.shortlink.dao.entity.ShortLinkDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.wgz.shortlink.dto.req.ShortLinkPageReqDTO;

/**
 * @author 下水道的小老鼠
 * &#064;description  针对表【t_link】的数据库操作Mapper
 * &#064;createDate  2024-08-02 11:22:12
 * &#064;Entity  generator.domain.TLink
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {

    @Update("update t_link set " +
            "total_uv = total_uv + #{totalUv}," +
            "total_pv = total_pv + #{totalPv}," +
            "total_uip = total_uip + #{totalUip} " +
            "where gid = #{gid} and full_short_url = #{fullShortUrl}")
    void incrementStats(
            @Param("gid") String gid,
            @Param("fullShortUrl") String fullShortUrl,
            @Param("totalUv") Integer totalUv,
            @Param("totalPv") Integer totalPv,
            @Param("totalUip") Integer totalUip
    );

    IPage<ShortLinkDO> pageLink(ShortLinkPageReqDTO requestParam);
}




