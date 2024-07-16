package org.wgz.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.wgz.shortlink.admin.common.convention.exception.ClientException;
import org.wgz.shortlink.admin.common.enums.UserErrorCodeEnums;
import org.wgz.shortlink.admin.dao.entity.UserDO;
import org.wgz.shortlink.admin.dao.mapper.UserMapper;
import org.wgz.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.wgz.shortlink.admin.dto.resp.UserRespDTO;
import org.wgz.shortlink.admin.service.UserService;

import static org.wgz.shortlink.admin.common.enums.UserErrorCodeEnums.USER_INSERT_ERROR;
import static org.wgz.shortlink.admin.common.enums.UserErrorCodeEnums.USER_NAME_EXIST;

/**
 * @author 下水道的小老鼠
 * &#064;description  针对表【t_user】的数据库操作Service实现
 * &#064;createDate  2024-05-23 14:53:47
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO>
        implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnums.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO userRegisterReqDTO) {
        if (hasUsername(userRegisterReqDTO.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        int inserted = baseMapper.insert(BeanUtil.toBean(userRegisterReqDTO, UserDO.class));
        if (inserted < 1) {
            throw new ClientException(USER_INSERT_ERROR);
        }
        userRegisterCachePenetrationBloomFilter.add(userRegisterReqDTO.getUsername());
    }
}




