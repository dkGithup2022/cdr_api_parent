package com.dk0124.cdr.api.endpoint.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CandleControllerTest {

    @Autowired
    MockMvc mockMvc;
    // mocking 의 dispatcher servlet 생성
    // slicing test, web 서버 안띄움 ( 톰 캣 )

    @Test
    public void emptyCall() throws Exception {
        mockMvc.perform(get("/cdrapi/candle/any/word")
                .accept(MediaType.APPLICATION_JSON)
                .header("key","702")
        )
                .andExpect(status().is(200));
    }

    @Test
    public void candleCallTest() throws Exception {
        mockMvc.perform(get("/cdrapi/candle/upbit/KRW-BTC?size=300&timestamp=1666405865159")
                .accept(MediaType.APPLICATION_JSON)
                .header("key","702")
        )
                .andExpect(status().is(200))
                .andDo(print());
        ;
    }

    @Test
    public void candleCallTestWithNoQueryParam() throws Exception {
        mockMvc.perform(get("/cdrapi/candle/upbit/KRW-BTC")
                .accept(MediaType.APPLICATION_JSON)
                .header("key","702")
        )
                .andExpect(status().is(200))
                .andDo(print());
        ;
    }

    @Test
    public void candleCallTestWithException() throws Exception {
        mockMvc.perform(get("/cdrapi/candle/upbit/KRW-BT")
                .accept(MediaType.APPLICATION_JSON)
                .header("key","702")
        )
                .andExpect(status().is(400))
                .andDo(print());
        ;
    }


}