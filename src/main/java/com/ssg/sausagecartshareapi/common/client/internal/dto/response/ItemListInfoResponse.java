package com.ssg.sausagecartshareapi.common.client.internal.dto.response;

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
public class ItemListInfoResponse {

    private HashMap<Long, ItemInfo> itemMap;

    @ToString
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ItemInfo {

        private Long itemId;
        private String itemBrandNm;
        private String itemNm;
        private int itemAmt;
        private String itemInvQty;
        private String itemImgUrl;
        private String shppCd;
    }
}
