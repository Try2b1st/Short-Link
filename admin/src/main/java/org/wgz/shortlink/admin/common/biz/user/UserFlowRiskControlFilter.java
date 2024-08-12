package org.wgz.shortlink.admin.common.biz.user;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import com.google.common.collect.Lists;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.wgz.shortlink.admin.common.convention.exception.ClientException;
import org.wgz.shortlink.admin.common.convention.result.Results;
import org.wgz.shortlink.admin.config.UserFlowRiskControlConfiguration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class UserFlowRiskControlFilter implements Filter {
    private final StringRedisTemplate stringRedisTemplate;

    private final UserFlowRiskControlConfiguration userFlowRiskControlConfiguration;

    private static final String USER_FLOW_RISK_CONTROL_LUA_SCRIPT_PATH = "lua/user_flow_risk_control.lua";

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        //设置lua脚本的文件地址
        redisScript.setScriptSource(
                new ResourceScriptSource(
                        new ClassPathResource(USER_FLOW_RISK_CONTROL_LUA_SCRIPT_PATH)));
        //设置lua脚本执行返回结果的类型
        redisScript.setResultType(Long.class);

        String username = Optional.ofNullable(UserContext.getUsername()).orElse("other");
        Long result;

        try {
            //执行lua脚本
            result = stringRedisTemplate.execute(redisScript, Lists.newArrayList(username), userFlowRiskControlConfiguration.getMaxAccessCount());

        } catch (Throwable throwable) {
            log.error("执行用户请求流量限制LUA脚本出错", throwable);
            returnJson((HttpServletResponse) servletResponse, JSON.toJSONString(Results.failure(new ClientException(USER_FLOW_RISK_CONTROL_LUA_SCRIPT_PATH))));
            return;
        }
        if (result == null || result > userFlowRiskControlConfiguration.getMaxAccessCount()) {
            returnJson((HttpServletResponse) servletResponse, JSON.toJSONString(Results.failure(new ClientException(USER_FLOW_RISK_CONTROL_LUA_SCRIPT_PATH))));
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void returnJson(HttpServletResponse response, String json) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        }
    }
}
