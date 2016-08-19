package cn.kxlove.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: happyhaha
 * @date: 16/8/19
 */
@Controller
public class MainController {

    @RequestMapping("/")
    private String index() {
        return "index";
    }
}
