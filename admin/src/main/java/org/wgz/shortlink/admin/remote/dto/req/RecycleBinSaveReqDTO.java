package org.wgz.shortlink.admin.remote.dto.req;

import lombok.Data;

/**
 * 短链接移至回收站请求参数
 */
@Data
public class RecycleBinSaveReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 完整短链接
     */
    private String fullShortUrl;
}
