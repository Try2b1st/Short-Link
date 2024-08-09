package org.wgz.shortlink.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wgz.shortlink.dao.entity.LinkNetworkStatsDO;
import org.wgz.shortlink.service.LinkNetworkStatsService;
import org.wgz.shortlink.dao.mapper.LinkNetworkStatsMapper;
import org.springframework.stereotype.Service;

/**
* @author 下水道的小老鼠
* @description 针对表【t_link_network_stats】的数据库操作Service实现
* @createDate 2024-08-09 14:39:46
*/
@Service
public class LinkNetworkStatsServiceImpl extends ServiceImpl<LinkNetworkStatsMapper, LinkNetworkStatsDO>
    implements LinkNetworkStatsService{

}




