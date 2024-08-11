package org.wgz.shortlink.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.wgz.shortlink.dao.entity.ShortLinkDO;
import org.wgz.shortlink.dto.req.ShortLinkPageReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkRecycleBinPageReqDTO;

/**
 * @author 下水道的小老鼠
 * &#064;description  针对表【t_link】的数据库操作Mapper
 * &#064;createDate  2024-08-02 11:22:12
 * &#064;Entity  generator.domain.TLink
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {

    void incrementStats(
            @Param("gid") String gid,
            @Param("fullShortUrl") String fullShortUrl,
            @Param("totalUv") Integer totalUv,
            @Param("totalPv") Integer totalPv,
            @Param("totalUip") Integer totalUip
    );

    IPage<ShortLinkDO> pageLink(ShortLinkPageReqDTO requestParam);

    /**
     * 分页统计回收站短链接
     */
    IPage<ShortLinkDO> pageRecycleBinLink(ShortLinkRecycleBinPageReqDTO requestParam);
}




