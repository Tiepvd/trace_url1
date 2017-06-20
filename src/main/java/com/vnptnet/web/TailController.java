package com.vnptnet.web;

import com.vnptnet.domain.Tag;
import com.vnptnet.service.data.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Vu Duc Tiep on 3/17/2017.
 */
@Controller
@RequestMapping("/files")
public class TailController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/home")
    public String home(Model model, Principal principal) {
        List<String> tags= new ArrayList<>();
        for (Tag tag: userService.findTagByUsername(principal.getName())){
            tags.add(tag.getName());
        }
        model.addAttribute("tags", tags);

        return "files/home";
    }
}
