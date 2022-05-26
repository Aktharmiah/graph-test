package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@RestController
public class GreetingController {

    private SimpMessagingTemplate simpMessagingTemplate;

    public GreetingController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println(message.getName());
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/test")
    @SendTo("/topic/greetings")
    public Greeting test() throws Exception {
        Thread.sleep(1000); // simulated delay
        String res = "Test";
        return new Greeting(res);
    }

//    @Async
    @Scheduled(fixedRate = 10000)
//    @SendTo("/topic/greetings")
    public void sendPeriodicMessages() {
        String broadcast = String.format("server periodic message %s via the broker", LocalTime.now());
        System.out.println(broadcast);
        simpMessagingTemplate.convertAndSend("/topic/greetings",
                broadcast);
//        return broadcast;
    }

//    @MessageMapping("/test")
//    @SendTo("/topic/greetings")
//    public Greeting newGreeting(HelloMessage message) throws Exception {
//        System.out.println("Message received: " + message.getMessage());
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, testing new message mapping.." + HtmlUtils.htmlEscape(message.getMessage()) + "!");
//    }

}