package com.ssg.sausagecartshareapi.common.client.internal;

import com.ssg.sausagecartshareapi.common.client.internal.dto.response.ItemListInfoResponse;
import com.ssg.sausagecartshareapi.common.dto.SuccessResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SAUSAGE-ITEM-API")
public interface ItemApiClient {

    @GetMapping("/api/item-list")
    SuccessResponse<ItemListInfoResponse> getItemListInfo(
            @RequestParam List<Long> itemIdList);
}
