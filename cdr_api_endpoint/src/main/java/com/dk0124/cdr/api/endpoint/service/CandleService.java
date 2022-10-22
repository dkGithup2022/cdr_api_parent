package com.dk0124.cdr.api.endpoint.service;


import com.dk0124.cdr.constants.coinCode.UpbitCoinCode.UpbitCoinCode;
import com.dk0124.cdr.entity.bithumb.candle.BithumbCandleFactory;
import com.dk0124.cdr.entity.upbit.candle.UpbitCandle;
import com.dk0124.cdr.repository.upbit.upbitCandleRepository.UpbitCandleCommonJpaInterface;
import com.dk0124.cdr.repository.upbit.upbitCandleRepository.UpbitCandleKrwAtomRepository;
import com.dk0124.cdr.repositoryPicker.bithumb.BithumbCandleRepositoryPicker;
import com.dk0124.cdr.repositoryPicker.upbit.UpbitCandleRepositoryPicker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandleService {
    private final UpbitCandleRepositoryPicker upbitCandleRepositoryPicker;
    private final BithumbCandleRepositoryPicker bithumbCandleRepositoryPicker;

    public List<UpbitCandle> getUpbitCandlesBefore(UpbitCoinCode code, Long timestamp, int size){

    }

}
