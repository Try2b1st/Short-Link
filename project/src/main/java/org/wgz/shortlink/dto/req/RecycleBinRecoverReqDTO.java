package org.wgz.shortlink.dto.req;

import lombok.Data;

@Data
public class RecycleBinRecoverReqDTO {

    private String gid;

    private String fullShortUrl;
}
