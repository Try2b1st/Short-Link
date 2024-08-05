package org.wgz.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.wgz.shortlink.admin.common.convention.result.Result;
import org.wgz.shortlink.admin.remote.dto.req.*;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.wgz.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

import java.util.HashMap;
import java.util.List;
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

    /**
     * 查询分组短链接数目
     *
     * @param requestParam gid列表请求参数
     * @return 响应参数
     */
    default Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(List<String> requestParam) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("requestParam", requestParam);
        String resultStr = HttpUtil.get("http://127.0.0.1:9000/api/shortLink/v1/count", requestMap);
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }

    /**
     * 修改短链接
     *
     * @param shortLinkUpdateReqDTO 修改短链接请求参数
     */
    default void updateShortLink(ShortLinkUpdateReqDTO shortLinkUpdateReqDTO) {
        HttpUtil.post("http://127.0.0.1:9000/api/shortLink/v1/update", JSON.toJSONString(shortLinkUpdateReqDTO));
    }


    /**
     * 根据 URL 获取网站标题
     *
     * @param url 网址
     * @return 网站标题
     */
    default Result<String> getTitleByUrl(String url) {
        String resultStr = HttpUtil.get("http://127.0.0.1:9000/api/shortLink/v1/title?url=" + url);
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }

    /**
     * 短链接移至回收站
     */
    default void saveRecycleBin(RecycleBinSaveReqDTO recycleBinSaveReqDTO) {
        HttpUtil.post("http://127.0.0.1:9000/api/shortLink/v1/recycle-bin/save", JSON.toJSONString(recycleBinSaveReqDTO));
    }

    /**
     * 分页查询短链接
     *
     * @param shortLinkRecycleBinPageReqDTO 查询参数
     * @return 短链接数据列表
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO shortLinkRecycleBinPageReqDTO) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gidList", shortLinkRecycleBinPageReqDTO.getGidList());
        requestMap.put("current", shortLinkRecycleBinPageReqDTO.getCurrent());
        requestMap.put("size", shortLinkRecycleBinPageReqDTO.getSize());

        String resultPageStr = HttpUtil.get("http://127.0.0.1:9000/api/shortLink/v1/recycle-bin/page", requestMap);
        return JSON.parseObject(resultPageStr, new TypeReference<>() {
        });
    }

    default void recoverRecycleBin(RecycleBinRecoverReqDTO recycleBinRecoverReqDTO) {
        HttpUtil.post("http://localhost:9000/api/shortLink/v1/recycle-bin/recover", JSON.toJSONString(recycleBinRecoverReqDTO));
    }

    default void removeRecycleBin(RecycleBinRemoveReqDTO recycleBinRemoveReqDTO) {
        HttpUtil.post("http://localhost:9000/api/shortLink/v1/recycle-bin/remove", JSON.toJSONString(recycleBinRemoveReqDTO));
    }
}
