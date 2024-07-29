package org.wgz.shortlink.admin.dto.req;

import lombok.Data;

@Data
public class ShortLinkGroupSortReqDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 排序字段
     */
    private Integer sortOrder;
}
