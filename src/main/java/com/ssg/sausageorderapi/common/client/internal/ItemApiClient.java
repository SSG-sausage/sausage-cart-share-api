package com.ssg.sausageorderapi.common.client.internal;

import com.ssg.sausageorderapi.common.client.internal.dto.response.ItemListInfoResponse;
import com.ssg.sausageorderapi.common.dto.SuccessResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ItemApiClient", url = "http://localhost:8081/api")
public interface ItemApiClient {

    @GetMapping("/item-list")
    SuccessResponse<ItemListInfoResponse> getItemListInfo(
            @RequestParam List<Long> itemIdList);
}
