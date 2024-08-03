package org.wgz.shortlink.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VailDateTypeEnum {

    /**
     * 永久
     */
    PERMANENT(0),

    /**
     * 临时
     */
    CUSTOM(1);

    private final Integer type;
}
