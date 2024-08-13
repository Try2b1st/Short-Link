package org.wgz.shortlink.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.wgz.shortlink.common.convention.result.Result;
import org.wgz.shortlink.dto.req.ShortLinkCreateReqDTO;
import org.wgz.shortlink.dto.resp.ShortLinkCreateRespDTO;

/**
 * 自定义分控规则
 */
public class CustomBlockHandler {
    public static Result<ShortLinkCreateRespDTO> createShortLinkBlockHandlerMethod(
            ShortLinkCreateReqDTO shortLinkCreateReqDTO,
            BlockException blockException){
        return new Result<ShortLinkCreateRespDTO>().setCode("B1000000").setMessage("当前访问网站人数过多，请稍后再试...");
    }
}
