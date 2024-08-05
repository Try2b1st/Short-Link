package org.wgz.shortlink.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wgz.shortlink.dao.entity.ShortLinkDO;
import org.wgz.shortlink.dto.req.RecycleBinRecoverReqDTO;
import org.wgz.shortlink.dto.req.RecycleBinSaveReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkPageRespDTO;

/**
 * @author 下水道的小老鼠
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * 短链接移至回收站
     */
    void saveRecycleBin(RecycleBinSaveReqDTO recycleBinSaveReqDTO);


    /**
     * 回收站 短链接分页
     *
     * @param shortLinkRecycleBinPageReqDTO 分页请求参数
     * @return 响应分页数据
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO shortLinkRecycleBinPageReqDTO);

    /**
     * 从回收站回复短链接
     *
     * @param recycleBinRecoverReqDTO 请求参数
     */
    void recoverRecycleBin(RecycleBinRecoverReqDTO recycleBinRecoverReqDTO);
}
