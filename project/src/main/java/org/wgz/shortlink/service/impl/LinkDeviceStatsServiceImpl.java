package org.wgz.shortlink.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wgz.shortlink.dao.entity.LinkDeviceStatsDO;
import org.wgz.shortlink.service.LinkDeviceStatsService;
import org.wgz.shortlink.dao.mapper.LinkDeviceStatsMapper;
import org.springframework.stereotype.Service;

/**
* @author 下水道的小老鼠
* @description 针对表【t_link_device_stats】的数据库操作Service实现
* @createDate 2024-08-09 13:24:52
*/
@Service
public class LinkDeviceStatsServiceImpl extends ServiceImpl<LinkDeviceStatsMapper, LinkDeviceStatsDO>
    implements LinkDeviceStatsService{

}




