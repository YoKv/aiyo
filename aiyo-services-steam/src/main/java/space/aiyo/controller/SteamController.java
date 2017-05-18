package space.aiyo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yo on 2017/5/17.
 */

@RestController
@RequestMapping("/steamDemo")
public class SteamController {

    @GetMapping(value = "/getUserMatch")
    public String getUserMatch(HttpServletRequest request){
//        ModelAndView mv = new ModelAndView();

        return "test";
    }

}
