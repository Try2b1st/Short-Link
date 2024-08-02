package org.wgz.shortlink.service;

import org.wgz.shortlink.dao.entity.ShortLinkDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wgz.shortlink.dto.req.ShortLinkCreateReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkCreateRespDTO;

/**
* @author 下水道的小老鼠
* @description 针对表【t_link】的数据库操作Service
* @createDate 2024-08-02 11:22:12
*/
public interface ShortLinkService extends IService<ShortLinkDO> {
    ShortLinkCreateRespDTO create(ShortLinkCreateReqDTO shortLinkCreateReqDTO);
}
