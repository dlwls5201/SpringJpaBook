package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    /**
     * @Slf4j 어노테이션이 자동으로 log 생성해 줍니다.
     */
    //private Logger log = LoggerFactory.getLogger(getClass());
    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }
}
