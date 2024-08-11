package org.wgz.shortlink.common.constant;

public class RedisKeyConstant {

    /**
     * 短链接跳转缓存前缀
     * 防止缓存击穿
     */
    public static final String GOTO_SHORT_LINK_KEY = "short-link_goto_%s";

    /**
     * 为布隆过滤器误判兜底
     */
    public static final String GOTO_IS_NULL_SHORT_LINK_KEY = "short-link_goto_is_null_%s";

    /**
     * 短链接跳转缓存锁前缀
     * 防止缓存击穿
     */
    public static final String LOCK_GOTO_SHORT_LINK_KEY = "short-link_lock_goto_%s";

    /**
     * 短链接修改分组 ID 锁前缀 Key
     */
    public static final String LOCK_GID_UPDATE_KEY = "short-link_lock_update-gid_%s";

    /**
     * 短链接延迟队列消费统计 Key
     */
    public static final String DELAY_QUEUE_STATS_KEY = "short-link_delay-queue:stats";
}
