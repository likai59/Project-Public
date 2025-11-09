package com.gtalent.ICUReceiver.configuration;


import com.gtalent.ICUReceiver.handler.ICUSignalWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
    @Autowired
    private ICUSignalWebSocketHandler Handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        // setAllowedOrigins("*"): 允許所有來源，開發階段，正式環境建議做限制
        registry.addHandler(Handler, "/ws/dynamic").setAllowedOrigins("*");
    }
}
