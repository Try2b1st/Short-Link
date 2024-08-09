package org.wgz.shortlink.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wgz.shortlink.dao.entity.LinkAccessLogsDO;
import org.wgz.shortlink.service.LinkAccessLogsService;
import org.wgz.shortlink.dao.mapper.LinkAccessLogsMapper;
import org.springframework.stereotype.Service;

/**
* @author 下水道的小老鼠
* @description 针对表【t_link_access_logs】的数据库操作Service实现
* @createDate 2024-08-09 13:09:01
*/
@Service
public class LinkAccessLogsServiceImpl extends ServiceImpl<LinkAccessLogsMapper, LinkAccessLogsDO>
    implements LinkAccessLogsService {

}




