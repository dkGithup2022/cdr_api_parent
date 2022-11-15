package com.dk0124.cdr.api.endpoint.controller;

import com.dk0124.cdr.constants.coinCode.UpbitCoinCode.UpbitCoinCode;
import com.dk0124.cdr.persistence.entity.upbit.tick.coins.UpbitTickKrwAda;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class UtilController {

    private final ObjectMapper objectMapper;

    @RequestMapping("/empty")
    public String emptyController(){
        return "ok";
    }


    @RequestMapping("/linking")
    public ResponseEntity linkingController() throws JsonProcessingException {

        UpbitTickKrwAda upbitTickKrwAda = new UpbitTickKrwAda();
        upbitTickKrwAda.setSequentialId(100L);
        upbitTickKrwAda.setCode(UpbitCoinCode.KRW_ADA.toString());
        String str = objectMapper.writeValueAsString(upbitTickKrwAda);


        URI createdURI = linkTo(methodOn(UtilController.class)
                .linkingController())
                .slash("empty")
                .toUri();

        return ResponseEntity
                .created(createdURI)
                .contentType(MediaType.APPLICATION_JSON)
                .body(str);
    }
}
