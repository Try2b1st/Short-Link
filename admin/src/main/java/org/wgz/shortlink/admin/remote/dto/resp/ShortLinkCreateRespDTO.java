package org.wgz.shortlink.admin.remote.dto.resp;

import lombok.Builder;
import lombok.Data;

/**
 * 创建短链接响应对象
 */
@Data
@Builder
public class ShortLinkCreateRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 短链接
     */
    private String fullShortUrl;
}
