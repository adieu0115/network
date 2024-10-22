package drghub.hello.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

@RestController
public class RequestController
{
    @Autowired
    public HttpServletRequest request;

    @GetMapping("/request/headers")
    public String requestHeaders()
    {
        HashMap<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder sb = new StringBuilder();

        if (headerNames != null)
        {
            while (headerNames.hasMoreElements())
            {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                headers.put(headerName, headerValue);
            }

            for (Map.Entry<String, String> e : headers.entrySet())
            {
                sb.append(String.format("%s : %s<br>", e.getKey(), e.getValue()));
            }
        }
        return sb.toString();
    }
}
