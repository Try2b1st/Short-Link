package org.wgz.shortlink.admin.service;

import org.wgz.shortlink.admin.dao.entity.GroupDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 下水道的小老鼠
* &#064;description  针对表【t_group】的数据库操作Service
* &#064;createDate  2024-07-17 14:18:47
 */
public interface GroupService extends IService<GroupDO> {

    void saveGroup(String groupName);
}
