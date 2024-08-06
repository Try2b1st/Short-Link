package org.wgz.shortlink.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wgz.shortlink.dao.entity.LinkAccessStatsDO;
import org.wgz.shortlink.service.LinkAccessStatsService;
import org.wgz.shortlink.dao.mapper.LinkAccessStatsMapper;
import org.springframework.stereotype.Service;

/**
* @author 下水道的小老鼠
* @description 针对表【t_link_access_stats】的数据库操作Service实现
* @createDate 2024-08-06 13:15:01
*/
@Service
public class LinkAccessStatsServiceImpl extends ServiceImpl<LinkAccessStatsMapper, LinkAccessStatsDO>
    implements LinkAccessStatsService {

}




