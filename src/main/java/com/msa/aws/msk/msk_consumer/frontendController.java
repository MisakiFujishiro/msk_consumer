package com.msa.aws.msk.msk_consumer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class frontendController {

    @RequestMapping(value="/msk-consumer", produces = "text/plain")
    public String frontend(){
        return "Hello msk-consumer!";
    }
}