package com.dk0124.cdr.api.endpoint.service;


import com.dk0124.cdr.constants.coinCode.CoinCode;
import com.dk0124.cdr.constants.coinCode.UpbitCoinCode.UpbitCoinCode;
import com.dk0124.cdr.constants.coinCode.bithumbCoinCode.BithumbCoinCode;
import com.dk0124.cdr.constants.vendor.VendorType;
import com.dk0124.cdr.persistence.entity.abstraction.Candle;
import com.dk0124.cdr.persistence.entity.bithumb.candle.BithumbCandle;
import com.dk0124.cdr.persistence.entity.upbit.candle.UpbitCandle;
import com.dk0124.cdr.persistence.repository.bithumb.bithumbCandleRepository.BithumbCandleCommonJpaInterface;
import com.dk0124.cdr.persistence.repository.upbit.upbitCandleRepository.UpbitCandleCommonJpaInterface;
import com.dk0124.cdr.persistence.repositoryPicker.bithumb.BithumbCandleRepositoryPicker;
import com.dk0124.cdr.persistence.repositoryPicker.upbit.UpbitCandleRepositoryPicker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandleService {
    private final UpbitCandleRepositoryPicker upbitCandleRepositoryPicker;
    private final BithumbCandleRepositoryPicker bithumbCandleRepositoryPicker;

    public List<? extends Candle> getCandles(VendorType vendorType, CoinCode code, Long timestamp, int size) {
        return vendorType == VendorType.BITHUMB ?
                getBithumbCandlesBefore((BithumbCoinCode) code, timestamp, size) :
                getUpbitCandlesBefore((UpbitCoinCode) code, timestamp, size);
    }


    private List<UpbitCandle> getUpbitCandlesBefore(UpbitCoinCode code, Long timestamp, int size) {
        UpbitCandleCommonJpaInterface repo = upbitCandleRepositoryPicker.getRepositoryFromCode(code);
        PageRequest pageRequest = PageRequest.of(0, size, Sort.by("timestamp").descending());
        return repo.findByTimestampLessThanEqual(timestamp, pageRequest);
    }

    private List<BithumbCandle> getBithumbCandlesBefore(BithumbCoinCode code, Long timestamp, int size) {
        BithumbCandleCommonJpaInterface repo = bithumbCandleRepositoryPicker.getRepositoryFromCode(code);
        PageRequest pageRequest = PageRequest.of(0, size, Sort.by("timestamp").descending());
        return repo.findByTimestampLessThanEqual(timestamp, pageRequest);

    }


}
