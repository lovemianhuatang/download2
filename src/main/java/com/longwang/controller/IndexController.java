package com.longwang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longwang
 * @create 2018-10-25 10:45
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
