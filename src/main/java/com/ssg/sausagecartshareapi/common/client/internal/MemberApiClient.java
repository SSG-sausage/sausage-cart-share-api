package com.ssg.sausagecartshareapi.common.client.internal;

import com.ssg.sausagecartshareapi.common.client.internal.dto.response.MbrListInfoResponse;
import com.ssg.sausagecartshareapi.common.dto.SuccessResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MemberApiClient", url = "http://localhost:8080/api")
public interface MemberApiClient {

    @GetMapping("/mbr-list")
    SuccessResponse<MbrListInfoResponse> getMbrListInfo(
            @RequestParam List<Long> mbrIdList);
}
