package org.wgz.shortlink.admin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录响应
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRespDTO {

    /**
     * token
     */
    String token;
}
