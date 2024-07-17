package org.wgz.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.wgz.shortlink.admin.dao.entity.GroupDO;
import org.wgz.shortlink.admin.service.GroupService;
import org.wgz.shortlink.admin.dao.mapper.GroupMapper;
import org.springframework.stereotype.Service;
import org.wgz.shortlink.admin.util.RandomStringGenerator;

/**
 * @author 下水道的小老鼠
 * &#064;description  针对表【t_group】的数据库操作Service实现
 * &#064;createDate  2024-07-17 14:18:47
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO>
        implements GroupService {

    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomStringGenerator.generateRandomString();
        } while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .name(groupName)
                // TODO 设置用户名
                .username("wgzwgz")
                .build();
    }

    private Boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                // TODO 设置用户名
                .eq(GroupDO::getUsername, "wgzwgz");
        GroupDO hasGroup = baseMapper.selectOne(queryWrapper);
        return hasGroup != null;
    }
}




