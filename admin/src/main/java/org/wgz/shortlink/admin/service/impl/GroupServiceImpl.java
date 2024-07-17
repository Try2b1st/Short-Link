package org.wgz.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.wgz.shortlink.admin.common.biz.user.UserContext;
import org.wgz.shortlink.admin.dao.entity.GroupDO;
import org.wgz.shortlink.admin.dto.resp.ShortLinkGroupListRespDTO;
import org.wgz.shortlink.admin.service.GroupService;
import org.wgz.shortlink.admin.dao.mapper.GroupMapper;
import org.springframework.stereotype.Service;
import org.wgz.shortlink.admin.util.RandomStringGenerator;

import java.util.List;

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
        } while (hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .name(groupName)
                .sortOrder(0)
                .username(UserContext.getUsername())
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupListRespDTO> groupList() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder)
                .orderByDesc(GroupDO::getUpdateTime);
        List<GroupDO> groupDOS = baseMapper.selectList(queryWrapper);

        return BeanUtil.copyToList(groupDOS, ShortLinkGroupListRespDTO.class);
    }

    private Boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, UserContext.getUsername());
        GroupDO hasGroup = baseMapper.selectOne(queryWrapper);
        return hasGroup != null;
    }
}




