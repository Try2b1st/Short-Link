package org.wgz.shortlink.admin.remote.dto.req;

import lombok.Data;

@Data
public class RecycleBinRecoverReqDTO {

    private String gid;

    private String fullShortUrl;
}
