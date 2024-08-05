package org.wgz.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

public interface RecycleBinService {
    Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO shortLinkRecycleBinPageReqDTO);
}
