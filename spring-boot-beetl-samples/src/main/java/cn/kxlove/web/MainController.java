package cn.kxlove.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: happyhaha
 * @date: 16/8/19
 */
@Controller
public class MainController {

    @RequestMapping("/")
    private String index(Model model) {
        model.addAttribute("happy","Hello,world");
        return "index";
    }
}
