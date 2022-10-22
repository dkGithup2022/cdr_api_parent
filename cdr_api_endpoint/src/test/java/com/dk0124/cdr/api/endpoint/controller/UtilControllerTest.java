package com.dk0124.cdr.api.endpoint.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UtilControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void empty() throws Exception{
        mockMvc.perform(get("/empty")
        .header("key",702))
                .andExpect(status().is(200));
    }

    @Test
    public void linking() throws Exception{
        mockMvc.perform(get("/linking")
                .header("key",702))
                .andExpect(status().isCreated())
                .andDo(print())
        ;
    }








}