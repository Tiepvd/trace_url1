package com.vnptnet.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vu Duc Tiep on 3/17/2017.
 */
@Controller
public class RootController {
    @RequestMapping("/")
    public String onRootAccess() {
        return "redirect:/files/home";
    }
}