package org.wgz.shortlink.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.wgz.shortlink.common.constant.SentinelRuleConstant.CREATE_SHORT_LINK_RULE;

/**
 * 初始化限流配置
 */
@Component
public class SentinelRuleConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        List<FlowRule> flowRules = new ArrayList<>();

        // 创建风控规则
        FlowRule createFlowRule = new FlowRule();
        createFlowRule.setResource(CREATE_SHORT_LINK_RULE);
        // 根据 QPS 进行风控
        createFlowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        createFlowRule.setCount(1);
        flowRules.add(createFlowRule);

        FlowRuleManager.loadRules(flowRules);
    }
}
