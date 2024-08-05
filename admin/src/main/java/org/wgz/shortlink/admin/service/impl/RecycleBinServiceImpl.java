package org.wgz.shortlink.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wgz.shortlink.admin.common.biz.user.UserContext;
import org.wgz.shortlink.admin.common.convention.exception.ClientException;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.dao.entity.GroupDO;
import org.wgz.shortlink.admin.dao.mapper.GroupMapper;
import org.wgz.shortlink.admin.remote.ShortLinkRemoteService;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.wgz.shortlink.admin.service.RecycleBinService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {

    private final GroupMapper groupMapper;
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @Override
    public Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO shortLinkRecycleBinPageReqDTO) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(groupDOList)) {
            throw new ClientException("用户没有分组信息");
        }
        shortLinkRecycleBinPageReqDTO.setGidList(groupDOList.stream().map(GroupDO::getGid).toList());
        return shortLinkRemoteService.pageRecycleBinShortLink(shortLinkRecycleBinPageReqDTO);
    }
}
