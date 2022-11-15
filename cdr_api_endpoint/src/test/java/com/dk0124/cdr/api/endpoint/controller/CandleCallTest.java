package com.dk0124.cdr.api.endpoint.controller;


import com.dk0124.cdr.api.endpoint.service.CandleService;
import com.dk0124.cdr.persistence.repositoryPicker.bithumb.BithumbCandleRepositoryPicker;
import com.dk0124.cdr.persistence.repositoryPicker.upbit.UpbitCandleRepositoryPicker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.MediaTypes;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test-h2")
@WebMvcTest
public class CandleCallTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CandleService candleService;

    @Test
    @DisplayName("빈 테스트 내용")
    public void empty() {
        System.out.println("this is empty test");
        assertNotNull(mockMvc);
    }

    @Test
    @DisplayName("정상적 요청 / 200 response 확인 ")
    public void goodRes200Code() throws Exception {

        mockMvc.perform(
                get("/cdrapi/candle/upbit/KRW-BTC?size=300&timestamp=1666405865159")
                        .accept(MediaTypes.HAL_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }


}
