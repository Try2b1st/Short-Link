package org.wgz.shortlink.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wgz.shortlink.dao.entity.LinkBrowserStatsDO;
import org.wgz.shortlink.service.LinkBrowserStatsService;
import org.wgz.shortlink.dao.mapper.LinkBrowserStatsMapper;
import org.springframework.stereotype.Service;

/**
* @author 下水道的小老鼠
* @description 针对表【t_link_browser_stats】的数据库操作Service实现
* @createDate 2024-08-09 12:26:02
*/
@Service
public class LinkBrowserStatsServiceImpl extends ServiceImpl<LinkBrowserStatsMapper, LinkBrowserStatsDO>
    implements LinkBrowserStatsService {

}




