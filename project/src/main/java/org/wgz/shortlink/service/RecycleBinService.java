package org.wgz.shortlink.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wgz.shortlink.dao.entity.ShortLinkDO;
import org.wgz.shortlink.dto.req.RecycleBinSaveReqDTO;

/**
* @author 下水道的小老鼠
 */
public interface RecycleBinService extends IService<ShortLinkDO> {
    void saveRecycleBin(RecycleBinSaveReqDTO recycleBinSaveReqDTO);

}
