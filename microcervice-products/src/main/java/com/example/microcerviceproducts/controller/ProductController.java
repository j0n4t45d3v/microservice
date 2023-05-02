package com.example.microcerviceproducts.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    @GetMapping
    public String get(){
        return "api product";
    }
}
