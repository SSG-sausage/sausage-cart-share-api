package com.ssg.sausageorderapi.common.client.internal.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MbrListInfoResponse {

    private HashMap<Long, MbrInfo> mbrMap;

    @ToString
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MbrInfo {

        private Long mbrId;
        private String mbrLoginId;
        private String mbrNm;
        private String mbrImgUrl;
        private String mbrHpno;
    }
}
