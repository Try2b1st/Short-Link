package org.wgz.shortlink.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网关错误返回信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GatewayErrorResult {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 错误消息
     */
    private String message;
}
