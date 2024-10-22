package drghub.hello.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class RttController
{
    @GetMapping("/rtt-time-trace")
    public String handlerInitialTimeTraceRequest(HttpServletRequest request, HttpSession session)
    {
        long requestReceivedTime = System.currentTimeMillis();

        session.setAttribute("requestReceivedTime", requestReceivedTime);

        return "redirect:/api/redirect-time-trace";
    }

    @GetMapping("/redirect-time-trace")
    @ResponseBody
    public String handleRedirectTimeTraceRequest(HttpSession session)
    {
        Long requestReceivedTime = (Long) session.getAttribute("requestReceivedTime");
        long responseSentTime = System.currentTimeMillis();

        // RTT 계산
        if (requestReceivedTime != null)
        {
            long rtt = responseSentTime - requestReceivedTime;
            return "RTT (Round Trip Time) is: " + rtt + " ms";
        }
        else
        {
            return "Initial request time not found.";
        }
    }
}
