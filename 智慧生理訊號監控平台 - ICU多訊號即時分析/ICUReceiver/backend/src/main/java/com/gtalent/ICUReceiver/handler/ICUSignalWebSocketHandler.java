package com.gtalent.ICUReceiver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtalent.ICUReceiver.model.ICUSignal;
import com.gtalent.ICUReceiver.model.ICUSignalPayload;
import com.gtalent.ICUReceiver.repository.ICURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ICUSignalWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Autowired
    private ICURepository icuRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession){
        sessions.add(webSocketSession);
        System.out.println("已連線" + webSocketSession.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status){
        sessions.remove(webSocketSession);
        System.out.println("連線已關閉" + webSocketSession.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage message){
        String json = message.getPayload();
        System.out.println("資料:" + json);
        try{
            ObjectMapper mapper = new ObjectMapper();

            ICUSignalPayload payload = mapper.readValue(json, ICUSignalPayload.class);
            ICUSignal entity  = new ICUSignal(payload);
            icuRepository.save(entity);

            // 核心: 發送收到的 JSON 給所有連線 (可改回特定 session)
            broadcast(json);

        }catch (Exception e){
            System.out.println("JSON 解析失敗" + e.getMessage());
        }
    }

    public void broadcast(String json) {
        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(json));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
