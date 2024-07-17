package org.wgz.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wgz.shortlink.admin.dao.entity.GroupDO;
import org.wgz.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.wgz.shortlink.admin.dto.resp.ShortLinkGroupListRespDTO;

import java.util.List;

/**
* @author 下水道的小老鼠
* &#064;description  针对表【t_group】的数据库操作Service
* &#064;createDate  2024-07-17 14:18:47
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 新增短链接分组
     *
     * @param groupName 分组名称
     */
    void saveGroup(String groupName);

    /**
     * 获取短链接分组列表
     */
    List<ShortLinkGroupListRespDTO> groupList();

    void updateGroup(ShortLinkGroupUpdateReqDTO shortLinkGroupUpdateReqDTO);
}
