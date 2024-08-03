package org.wgz.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 短链接分页请求参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShortLinkPageReqDTO extends Page implements Serializable {

    /**
     * 分组标识
     */
    private String gid;

    @Serial
    private static final long serialVersionUID = 1L;
}
