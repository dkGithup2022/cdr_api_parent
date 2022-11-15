package com.dk0124.cdr.api.endpoint.controller;

import com.dk0124.cdr.api.endpoint.service.CandleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ActiveProfiles("test-h2")
class CandleControllerTest {

    @Autowired
    MockMvc mockMvc;
    // mocking 의 dispatcher servlet 생성
    // slicing test, web 서버 안띄움 ( 톰 캣 )

    @Test
    public void empty(){}

    @Test
    @DisplayName("에러 메세지 : 올바르지 않은 벤더 코드")
    public void emptyCall() throws Exception {
        mockMvc.perform(get("/cdrapi/candle/any/word")
                .accept(MediaType.APPLICATION_JSON)
                .header("key", "702")
        ).andExpect(status().is4xxClientError()).andDo(print());
    }

    @Test
    @DisplayName("candle call: 올바른 요청")
    public void candleCallTest() throws Exception {
        mockMvc.perform(get("/cdrapi/candle/upbit/KRW-BTC?size=300&timestamp=1666405865159")
                .accept(MediaType.APPLICATION_JSON)
                .header("key", "702")
        )
                .andExpect(status().is(200))
                .andDo(print());
        ;
    }

    @Test
    public void candleCallTestWithNoQueryParam() throws Exception {
        mockMvc.perform(get("/cdrapi/candle/upbit/KRW-BTC")
                .accept(MediaType.APPLICATION_JSON)
                .header("key", "702")
        )
                .andExpect(status().is(200))
                .andDo(print());
        ;
    }

    @Test
    @DisplayName("에러 메세지: 잘못된 코인 코드")
    public void candleCallTestWithException() throws Exception {
        mockMvc.perform(get("/cdrapi/candle/upbit/KRW-BT")
                .accept(MediaType.APPLICATION_JSON)
                .header("key", "702")
        )
                .andExpect(status().is(400))
                .andDo(print());
        ;
    }


}