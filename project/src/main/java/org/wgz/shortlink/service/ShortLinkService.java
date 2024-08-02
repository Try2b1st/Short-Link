package org.wgz.shortlink.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wgz.shortlink.dao.entity.ShortLinkDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wgz.shortlink.dto.req.ShortLinkCreateReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkPageReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkCreateRespDTO;
import org.wgz.shortlink.dto.resp.ShortLinkPageRespDTO;

/**
* @author 下水道的小老鼠
* @description 针对表【t_link】的数据库操作Service
* @createDate 2024-08-02 11:22:12
*/
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     *
     * @param shortLinkCreateReqDTO 短链接创建请求参数
     * @return 短链接创建信息
     */
    ShortLinkCreateRespDTO create(ShortLinkCreateReqDTO shortLinkCreateReqDTO);

    /**
     * 短链接分页
     *
     * @param shortLinkPageReqDTO 分页请求参数
     * @return 响应分页数据
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO);
}
