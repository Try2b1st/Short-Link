package org.wgz.shortlink.admin.common.biz.user;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.wgz.shortlink.admin.common.convention.exception.ClientException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.wgz.shortlink.admin.common.enums.UserErrorCodeEnums.USER_TOKEN_FAIL;

/**
 * 用户信息传输过滤器
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private static final List<String> IGNORE_URL = Lists.newArrayList(
            "/api/shortLink/admin/v1/user/login"
            , "/api/shortLink/admin/v1/user/has-username");

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURL = httpServletRequest.getRequestURI();
        if (!IGNORE_URL.contains(requestURL)) {
            String method = httpServletRequest.getMethod();
            if (!(Objects.equals(requestURL, "/api/shortLink/admin/v1/user") && Objects.equals(method, "POST"))) {
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if (!StrUtil.isAllNotBlank(username, token)) {
                    // TODO 网关响应前端
                    return;
                }
                Object userInfoJsonStr;
                try {
                    userInfoJsonStr = stringRedisTemplate.opsForHash().get("login_" + username, token);
                    if (userInfoJsonStr == null) {
                        // TODO 网关响应前端
                        throw new ClientException(USER_TOKEN_FAIL);
                    }
                } catch (Exception e) {
                    // TODO 网关响应前端
                    throw new ClientException(USER_TOKEN_FAIL);
                }
                UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
                UserContext.setUser(userInfoDTO);
            }
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }
}