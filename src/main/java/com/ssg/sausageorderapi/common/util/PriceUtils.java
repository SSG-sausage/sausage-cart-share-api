package com.ssg.sausageorderapi.common.util;

import java.text.DecimalFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceUtils {

    public static String toString(int price) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(price);
    }

    public static int toInt(String price) {
        return Integer.parseInt(price.replace(",", ""));
    }
}
