package me.spring.boot.controller;

import me.spring.boot.biz.WsMessage;
import me.spring.boot.biz.WsResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author baiyu
 * <p>
 * webSocket示例
 */
@Controller
public class WebSocketController {


    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WsResponse say(WsMessage message) {
        return new WsResponse("welcome " + message.getContent());
    }


    @GetMapping("/demo/websocket")
    public String index() {
        return "demo/websocket";
    }
}
