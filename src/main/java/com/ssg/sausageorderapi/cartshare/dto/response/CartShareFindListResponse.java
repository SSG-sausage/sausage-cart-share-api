package com.ssg.sausageorderapi.cartshare.dto.response;

import com.ssg.sausageorderapi.cartshare.entity.CartShare;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class CartShareFindListResponse {

    private List<CartShareInfo> cartShareList;

    public static CartShareFindListResponse of(List<CartShare> cartShareList) {
        return CartShareFindListResponse.builder()
                .cartShareList(cartShareList.stream().map(CartShareInfo::of).collect(Collectors.toList()))
                .build();
    }
}
