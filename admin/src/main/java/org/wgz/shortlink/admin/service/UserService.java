package org.wgz.shortlink.admin.service;

import org.wgz.shortlink.admin.dao.entity.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wgz.shortlink.admin.dto.resp.UserRespDTO;

/**
* @author 下水道的小老鼠
* &#064;description  针对表【t_user】的数据库操作Service
* &#064;createDate  2024-05-23 14:53:47
 */
public interface UserService extends IService<UserDO> {
    UserRespDTO getUserByUsername(String user);
}
