package com.ssg.sausagecartshareapi.common.client.internal;

import com.ssg.sausagecartshareapi.common.client.internal.dto.response.MbrListInfoResponse;
import com.ssg.sausagecartshareapi.common.dto.SuccessResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SAUSAGE-MEMBER-API")
public interface MbrApiClient {

    @GetMapping("/api/mbr-list")
    SuccessResponse<MbrListInfoResponse> getMbrListInfo(
            @RequestParam List<Long> mbrIdList);
}
