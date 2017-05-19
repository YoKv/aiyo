package space.aiyo.controller;


import com.alibaba.fastjson.JSONObject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yo on 2017/5/17.
 */

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

class A{
      String a;
}

    /**
     * RequestBody待研究
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

        JSONObject fastobj = new JSONObject();
        fastobj.put("k", "v");
        lists.add(fastobj);

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

        JSONObject fastobj = new JSONObject();
        fastobj.put("k", "v");
        map.put("fastobj", fastobj);
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
