package org.wgz.shortlink.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.wgz.shortlink.dao.entity.ShortLinkDO;

import java.io.Serial;
import java.io.Serializable;

/**
 * 短链接分页请求参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> implements Serializable {

    /**
     * 分组标识
     */
    private String gid;

    @Serial
    private static final long serialVersionUID = 1L;
}
