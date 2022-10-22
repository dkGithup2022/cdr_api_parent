package com.dk0124.cdr.api.endpoint.controller;


import com.dk0124.cdr.constants.coinCode.CoinCode;
import com.dk0124.cdr.constants.coinCode.UpbitCoinCode.UpbitCoinCode;
import com.dk0124.cdr.constants.coinCode.bithumbCoinCode.BithumbCoinCode;
import com.dk0124.cdr.constants.vendor.VendorType;

import java.util.Arrays;
import java.util.Optional;

public class ParamValidator {


    public static VendorType validateVendorCode(String code) {
        VendorType vendorType = VendorType.fromString(code);
        if (vendorType == null)
            throw new IllegalArgumentException("vendor 코드가 올바르지 않음");
        return vendorType;
    }

    public static UpbitCoinCode validateUpbitCoinCode(String code) {
        UpbitCoinCode upbitCoinCode = UpbitCoinCode.fromString(code);
        if (upbitCoinCode == null)
            throw new IllegalArgumentException("coin 코드가 올바르지 않음");
        return upbitCoinCode;
    }

    public static BithumbCoinCode validateBithumbCoinCode(String code) {
        BithumbCoinCode bithumbCoinCode = BithumbCoinCode.fromString(code);
        if (bithumbCoinCode == null)
            throw new IllegalArgumentException("coin 코드가 올바르지 않음");
        return bithumbCoinCode;
    }

    public static Long validateTimestamp(Optional<String> timestamp) {
        Long t;
        if (timestamp.isPresent() && timestamp.get().chars().allMatch(Character::isDigit)) {
            t = Long.parseLong(timestamp.get());
        } else {
            t = System.currentTimeMillis();
        }
        if (t < 1664550000000L) {
            throw new IllegalArgumentException("2022년 10월 데이터부터 제공 합니다.");
        }
        return t;
    }

    public static int validateSize(Optional<String> size) {
        int s;
        if (size.isPresent() && size.get().chars().allMatch(Character::isDigit)) {
            s = Integer.parseInt(size.get());
            s = s > 1000 ? 1000 : s;
        } else {
            s = 200;
        }

        return s;
    }
}
