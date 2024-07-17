package org.wgz.shortlink.admin.common.biz.user;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 用户信息传输过滤器
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private static final List<String> IGNORE_URL = Lists.newArrayList(
            "/api/shortLink/v1/user/login"
            ,"/api/shortLink/v1/user/has-username");

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURL = httpServletRequest.getRequestURI();
        if(!IGNORE_URL.contains(requestURL)){
            String method = httpServletRequest.getMethod();
            if(!Objects.equals(requestURL,"/api/shortLink/v1/user")){
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                Object userInfoJsonStr = stringRedisTemplate.opsForHash().get("login_" + username, token);
                if(userInfoJsonStr != null){
                    UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(),UserInfoDTO.class);
                    UserContext.setUser(userInfoDTO);
                }
                try {
                    filterChain.doFilter(servletRequest, servletResponse);
                } finally {
                    UserContext.removeUser();
                }
            }
        }

    }
}