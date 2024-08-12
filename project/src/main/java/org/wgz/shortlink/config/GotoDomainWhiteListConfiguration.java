package org.wgz.shortlink.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "short-link.goto-domain.white-list")
public class GotoDomainWhiteListConfiguration {

    /**
     * 是否启用原始链接域名白名单验证
     */
    private Boolean enable;

    /**
     * 域名名称
     */
    private String names;

    /**
     * 可跳转链接域名白名单列表
     */
    private List<String> details;
}
