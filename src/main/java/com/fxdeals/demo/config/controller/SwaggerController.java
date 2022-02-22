package com.fxdeals.demo.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class SwaggerController {

    @RequestMapping("/swagger-ui")
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }

    @RequestMapping("/swagger-api")
    public String swaggerApi() {
        return "redirect:/v2/api-docs";
    }

}