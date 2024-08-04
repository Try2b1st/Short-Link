package org.wgz.shortlink.common.constant;

public class RedisKeyConstant {

    /**
     * 短链接跳转缓存前缀
     * 防止缓存击穿
     */
    public static final String GOTO_SHORT_LINK_KEY = "short-link_goto_%s";

    /**
     * 短链接跳转缓存锁前缀
     * 防止缓存击穿
     */
    public static final String LOCK_GOTO_SHORT_LINK_KEY = "short-link_lock_goto_%s";
}
