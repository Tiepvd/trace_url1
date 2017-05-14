package com.vnptnet.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;

/**
 * Created by Vu Duc Tiep on 3/17/2017.
 */
@Controller
@RequestMapping("/files")
public class TailController {
    private SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy/MM/dd");
    @RequestMapping("/home")
    public String home(Model model) {
        String filePath= "D:/tmp/";
//		model.addAttribute("filename", System.getProperty("java.io.tmpdir") + "quotes.txt");
        model.addAttribute("filename", filePath+"messages.log");
        return "files/home";
    }
}
