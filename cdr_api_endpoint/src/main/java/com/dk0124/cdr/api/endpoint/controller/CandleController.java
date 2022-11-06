package com.dk0124.cdr.api.endpoint.controller;


import com.dk0124.cdr.api.endpoint.service.CandleService;
import com.dk0124.cdr.constants.coinCode.CoinCode;
import com.dk0124.cdr.constants.vendor.VendorType;
import com.dk0124.cdr.entity.abstraction.Candle;
import com.dk0124.cdr.repositoryPicker.bithumb.BithumbCandleRepositoryPicker;
import com.dk0124.cdr.repositoryPicker.upbit.UpbitCandleRepositoryPicker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cdrapi/candle")
@RequiredArgsConstructor
public class CandleController {

    private final CandleService candleService;

    @RequestMapping("/{vendorCode}/{coinCode}")
    public ResponseEntity cdrApiCandle(
            @PathVariable String vendorCode,
            @PathVariable String coinCode,
            @RequestParam(required = false, name = "timestamp") Optional<String> timestampBeforeValidated,
            @RequestParam(required = false, name = "size") Optional<String> sizeBeforeValidated
    ) {

        // validation & fix parameter

        Long timestamp;
        Integer size;
        VendorType vendorType;
        CoinCode coin;
        try {
            timestamp = ParamValidator.validateTimestamp(timestampBeforeValidated);
            size = ParamValidator.validateSize(sizeBeforeValidated);

            vendorType = ParamValidator.validateVendorCode(vendorCode);

            coin = vendorType == VendorType.BITHUMB ?
                    ParamValidator.validateBithumbCoinCode(coinCode) :
                    ParamValidator.validateUpbitCoinCode(coinCode);

        } catch (IllegalArgumentException e) {
            return badRequest(e.getMessage());
        }

        //service

        List<Candle> candles = (List<Candle>) candleService.getCandles(vendorType,coin,timestamp,size);


        return  ResponseEntity(candles);

    }

    private ResponseEntity badRequest(String errorMessage) {
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
