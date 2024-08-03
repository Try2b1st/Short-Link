package org.wgz.shortlink.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.wgz.shortlink.dao.entity.ShortLinkDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wgz.shortlink.dto.req.ShortLinkCreateReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkPageReqDTO;
import org.wgz.shortlink.dto.req.ShortLinkUpdateReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkCreateRespDTO;
import org.wgz.shortlink.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.wgz.shortlink.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

/**
* @author 下水道的小老鼠
* &#064;description  针对表【t_link】的数据库操作Service
* &#064;createDate  2024-08-02 11:22:12
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     *
     * @param shortLinkCreateReqDTO 短链接创建请求参数
     * @return 短链接创建信息
     */
    ShortLinkCreateRespDTO create(ShortLinkCreateReqDTO shortLinkCreateReqDTO);

    /**
     * 短链接分页
     *
     * @param shortLinkPageReqDTO 分页请求参数
     * @return 响应分页数据
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO);

    /**
     * 查询短链接分组下的短链接数目
     *
     * @param requestParam 分组标识集合
     * @return 响应参数
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);


    /**
     * 修改短链接
     *
     * @param shortLinkUpdateReqDTO 修改短链接请求参数
     */
    void updateShortLink(ShortLinkUpdateReqDTO shortLinkUpdateReqDTO);

    /**
     * 短链接跳转
     *
     * @param shortUri 短链接
     * @param request HTTP 请求参数
     * @param response HTTP 响应
     */
    void restoreUrl(String shortUri, ServletRequest request, ServletResponse response);
}
