package space.aiyo.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.aiyo.demo.entity.Greeting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yo on 2017/5/19.
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
//    A key difference between a traditional MVC controller and the RESTful web service controller
//    above is the way that the HTTP response body is created.
//    Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML,
//    this RESTful web service controller simply populates and returns a Greeting object.
//    The object data will be written directly to the HTTP response as JSON.