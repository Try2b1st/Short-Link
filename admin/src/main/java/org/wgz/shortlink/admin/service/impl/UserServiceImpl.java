package org.wgz.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.wgz.shortlink.admin.common.convention.exception.ClientException;
import org.wgz.shortlink.admin.common.enums.UserErrorCode;
import org.wgz.shortlink.admin.dao.entity.UserDO;
import org.wgz.shortlink.admin.dao.mapper.UserMapper;
import org.wgz.shortlink.admin.dto.resp.UserRespDTO;
import org.wgz.shortlink.admin.service.UserService;

/**
 * @author 下水道的小老鼠
 * &#064;description  针对表【t_user】的数据库操作Service实现
 * &#064;createDate  2024-05-23 14:53:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO>
        implements UserService {

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null){
            throw new ClientException(UserErrorCode.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}




