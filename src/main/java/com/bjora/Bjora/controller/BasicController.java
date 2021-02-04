package com.bjora.Bjora.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/basic")
public class BasicController {
    public String testFunction() {
        return "hello";
    }
}
