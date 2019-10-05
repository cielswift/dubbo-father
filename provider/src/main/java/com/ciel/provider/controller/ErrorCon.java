package com.ciel.provider.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorCon implements ErrorController { //统一404

    @Override
    public String getErrorPath() {
        return null;
    }

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public ModelAndView error(Model model) {

        model.addAttribute("err","404");
        return new ModelAndView("/fram/error");
    }

}