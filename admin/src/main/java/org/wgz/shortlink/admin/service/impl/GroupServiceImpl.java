package org.wgz.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wgz.shortlink.admin.common.biz.user.UserContext;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.dao.entity.GroupDO;
import org.wgz.shortlink.admin.dao.mapper.GroupMapper;
import org.wgz.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.wgz.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.wgz.shortlink.admin.dto.resp.ShortLinkGroupListRespDTO;
import org.wgz.shortlink.admin.remote.ShortLinkRemoteService;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.wgz.shortlink.admin.service.GroupService;
import org.wgz.shortlink.admin.util.RandomStringGenerator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author 下水道的小老鼠
 * &#064;description  针对表【t_group】的数据库操作Service实现
 * &#064;createDate  2024-07-17 14:18:47
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO>
        implements GroupService {

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @Override
    public void saveGroup(String groupName) {
        saveGroup(UserContext.getUsername(), groupName);
    }

    @Override
    public void saveGroup(String username, String groupName) {
        String gid;
        do {
            gid = RandomStringGenerator.generateRandomString();
        } while (hasGid(username, gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .name(groupName)
                .sortOrder(0)
                .username(username)
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

        Result<List<ShortLinkGroupCountQueryRespDTO>> listResult = shortLinkRemoteService.listGroupShortLinkCount(
                groupDOS.stream().map(GroupDO::getGid).toList()
        );

        List<ShortLinkGroupListRespDTO> shortLinkGroupListRespDTOList = BeanUtil.copyToList(groupDOS, ShortLinkGroupListRespDTO.class);

        shortLinkGroupListRespDTOList.forEach(each -> {
            Optional<ShortLinkGroupCountQueryRespDTO> first = listResult.getData().stream()
                    .filter(item -> Objects.equals(item.getGid(), each.getGid()))
                    .findFirst();
            first.ifPresent(item -> each.setShortLinkCount(first.get().getShortLinkCount()));
            // TODO
//            first.ifPresentOrElse(
//                    item -> each.setShortLinkCount(item.getShortLinkCount()),
//                    () -> each.setShortLinkCount(0)
//            );
        });
        return shortLinkGroupListRespDTOList;
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO shortLinkGroupUpdateReqDTO) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, shortLinkGroupUpdateReqDTO.getGid());
        GroupDO groupDO = new GroupDO();
        groupDO.setName(shortLinkGroupUpdateReqDTO.getName());
        baseMapper.update(groupDO, updateWrapper);
    }

    @Override
    public void deleteGroup(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getDelFlag, 0);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> shortLinkGroupSortReqDTOS) {
        shortLinkGroupSortReqDTOS.forEach(each -> {
            LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                    .set(GroupDO::getSortOrder, each.getSortOrder())
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    .eq(GroupDO::getGid, each.getGid())
                    .eq(GroupDO::getDelFlag, 0);
            update(updateWrapper);
        });
    }

    private Boolean hasGid(String username, String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, Optional.ofNullable(username).orElse(UserContext.getUsername()));
        GroupDO hasGroup = baseMapper.selectOne(queryWrapper);
        return hasGroup != null;
    }
}




