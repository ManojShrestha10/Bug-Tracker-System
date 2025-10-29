package com.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/developer")
public class DeveloperController {
    
    // logger instance
    Logger logger = LoggerFactory.getLogger(DeveloperController.class);

    // Developer home page
    @RequestMapping("/home")
    public String developerHome() {
        return "/developer/home";
    }
    

}
