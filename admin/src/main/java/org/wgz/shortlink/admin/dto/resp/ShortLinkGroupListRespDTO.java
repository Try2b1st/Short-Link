package org.wgz.shortlink.admin.dto.resp;

import lombok.Data;

@Data
public class ShortLinkGroupListRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 分组排序
     */
    private Integer sortOrder;

    /**
     * 当前分组下的短链接数
     */
    private Integer num;
}
