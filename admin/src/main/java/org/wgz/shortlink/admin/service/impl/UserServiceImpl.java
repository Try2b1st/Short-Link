package org.wgz.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.wgz.shortlink.admin.common.convention.exception.ClientException;
import org.wgz.shortlink.admin.common.enums.UserErrorCodeEnums;
import org.wgz.shortlink.admin.dao.entity.UserDO;
import org.wgz.shortlink.admin.dao.mapper.UserMapper;
import org.wgz.shortlink.admin.dto.req.UserLoginReqDTO;
import org.wgz.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.wgz.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.wgz.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.wgz.shortlink.admin.dto.resp.UserRespDTO;
import org.wgz.shortlink.admin.service.GroupService;
import org.wgz.shortlink.admin.service.UserService;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import static org.wgz.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static org.wgz.shortlink.admin.common.enums.UserErrorCodeEnums.*;

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
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
    private final GroupService groupService;

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
        Lock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + userRegisterReqDTO.getUsername());
        try {
            if (lock.tryLock()) {
                try {
                    int inserted = baseMapper.insert(BeanUtil.toBean(userRegisterReqDTO, UserDO.class));
                    if (inserted < 1) {
                        throw new ClientException(USER_INSERT_ERROR);
                    }
                } catch (DuplicateKeyException e) {
                    throw new ClientException(USER_EXIST);
                }
                userRegisterCachePenetrationBloomFilter.add(userRegisterReqDTO.getUsername());
                groupService.saveGroup(userRegisterReqDTO.getUsername(), "默认分组");
                return;
            }
            throw new ClientException(USER_NAME_EXIST);
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void update(UserUpdateReqDTO userUpdateReqDTO) {
        // TODO 验证修改用户是否为当前登录用户
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers
                .lambdaUpdate(UserDO.class)
                .eq(UserDO::getUsername, userUpdateReqDTO.getUsername());
        baseMapper.update(BeanUtil.toBean(userUpdateReqDTO, UserDO.class), updateWrapper);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO) {
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>(UserDO.class)
                .eq(UserDO::getUsername, userLoginReqDTO.getUsername())
                .eq(UserDO::getPassword, userLoginReqDTO.getPassword());
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(USER_NULL);
        }
        Boolean hasLogin = stringRedisTemplate.hasKey("login_" + userLoginReqDTO.getUsername());
        if (hasLogin != null && hasLogin) {
            throw new ClientException(USER_IS_LOGIN);
        }

        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put("login_" + userLoginReqDTO.getUsername(), uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_" + userLoginReqDTO.getUsername(), 30L, TimeUnit.MINUTES);
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey("login_" + username)) &&
                stringRedisTemplate.opsForHash().get("login_" + username, token) != null;
    }

    @Override
    public void logout(String username, String token) {
        if (checkLogin(username, token)) {
            stringRedisTemplate.delete("login_" + username);
            return;
        }
        throw new ClientException(USER_NO_LOGIN_OR_NO_TOKEN);
    }
}




