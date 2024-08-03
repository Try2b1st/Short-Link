package org.wgz.shortlink.admin.remote.dto.resp;

import lombok.Data;

/**
 * 查询短链接分组短链接数目返回参数
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 短链接数目
     */
    private Integer shortLinkCount;
}
