package com.ciel.test;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aa")
public class Contr {

    @RequestMapping
    public String aa(){
        return "aa";

    }
}
