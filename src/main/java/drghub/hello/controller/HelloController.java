package drghub.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloController
{
    static final String message = "hello network";

    @RequestMapping("/network")
    public String hello()
    {
        return message;
    }
}
