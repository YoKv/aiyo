package space.aiyo.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import space.aiyo.demo.entity.Greeting;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yo on 2017/5/17.
 */


//This code uses Spring 4’s new
//@RestController annotation,which marks the
//class as a controller where every method returns a domain object instead of a view.
//It’s shorthand for@Controller and@ResponseBody rolled together.

/**
 * Spring-boot 请求demo
 */
@RestController
@RequestMapping("/steamDemo")
public class SteamController {
    /**
     * 只接受get
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/getUserMatch")
    public String getUserMatch(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        return "test";
    }

    class A {
        String a;
    }

    /**
     * RequestBody待研究
     *
     * @param a
     * @return
     */

    @RequestMapping(value = "/ajax")
    public List<Object> ajaxDemo(@RequestBody A a) {
        List<Object> lists = new ArrayList<>();
        lists.add("test");
        lists.add("test1");
        lists.add("test12");
        lists.add("test2323");
        lists.add("test23");
        Greeting greeting = new Greeting(1L, "ddd");
        lists.add(greeting);
        return lists;
    }

    @RequestMapping(value = "/ajaxMap")
    public Map<String, Object> ajaxMap(HttpServletRequest request,
                                       @RequestParam("b") String b,
                                       @RequestParam(name = "c", required = false) String c) {
        // System.out.println(b);
        System.out.println(c);

        Map map = new HashMap();
        map.put("Str", "sd");
        map.put("Strs", "sd");
        Greeting greeting = new Greeting(1L, "ddd");
//        As you see in steps below, Spring uses the
//        Jackson JSON library to automatically marshal instances of type Greeting into JSON.
        map.put("greeting", greeting);
        return map;
    }


    @RequestMapping(value = "/method1")
    public ModelAndView method1() {
        ModelAndView mv = new ModelAndView();
        Map map = new HashMap();
        map.put("Str", "sd");
        mv.addAllObjects(map);
        return mv;
    }

}
