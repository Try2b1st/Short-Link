package org.wgz.shortlink.admin.service;

import org.wgz.shortlink.admin.dao.entity.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wgz.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.wgz.shortlink.admin.dto.resp.UserRespDTO;

/**
 * @author 下水道的小老鼠
 * &#064;description  针对表【t_user】的数据库操作Service
 * &#064;createDate  2024-05-23 14:53:47
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @return 用户名存在返回 True 不存在返回 False
     */
    Boolean hasUsername(String username);

    /**
     * 注册用户
     *
     * @param userRegisterReqDTO 用户注册请求参数
     */
    void register(UserRegisterReqDTO userRegisterReqDTO);
}
