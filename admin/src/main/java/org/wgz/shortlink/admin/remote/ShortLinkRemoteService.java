package org.wgz.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.wgz.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

import java.util.HashMap;
import java.util.Map;

public interface ShortLinkRemoteService {

    /**
     * 创建短链接
     *
     * @param shortLinkCreateReqDTO 创建短链接请求参数
     * @return 创建返回参数
     */
    default Result<ShortLinkCreateRespDTO> create(ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        String resultBodyStr = HttpUtil.post("http://127.0.0.1:9000/api/shortLink/v1/create", JSON.toJSONString(shortLinkCreateReqDTO));
        return JSON.parseObject(resultBodyStr, new TypeReference<Result<ShortLinkCreateRespDTO>>() {
        });
    }

    /**
     * 分页查询短链接
     *
     * @param shortLinkPageReqDTO 查询参数
     * @return 短链接数据列表
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gid", shortLinkPageReqDTO.getGid());
        requestMap.put("current", shortLinkPageReqDTO.getCurrent());
        requestMap.put("size", shortLinkPageReqDTO.getSize());

        String resultPageStr = HttpUtil.get("http://127.0.0.1:9000/api/shortLink/v1/page", requestMap);
        return JSON.parseObject(resultPageStr, new TypeReference<>() {
        });
    }
}
