package drghub.hello.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController("/")
public class HelloController
{
    @Autowired
    public HttpServletRequest request;

    @GetMapping("/request/headers")
    public String requestHeaders()
    {
        Enumeration<String> headerNames = request.getHeaderNames();
        return headerNames.toString();
    }
}
