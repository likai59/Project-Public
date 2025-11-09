package com.gtalent;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class ICUSimulator {
    private static final Logger log = LoggerFactory.getLogger(ICUSimulator.class);
    public static void main( String[] args ) throws Exception
    {
        URI uri = new URI("ws://localhost:8080/ws/dynamic"); //WebSocket
        ObjectMapper objectMapper = new ObjectMapper();


        WebSocketClient wsc = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("連線成功");
                new Timer().scheduleAtFixedRate(
                        new TimerTask() {
                            @Override
                            public void run() {
                                try{
                                    Map<String, Object> data = new HashMap<>();
                                    data.put("nationalId", "001");
                                    data.put("heartRate", 60 + new Random().nextInt(40));
                                    data.put("pulse", 50 + new Random().nextInt(10));
                                    data.put("timestamp", LocalDateTime.now().toString());
                                    data.put("ecg", generateECG());
                                    //Map 轉 JSON
                                    String json = objectMapper.writeValueAsString(data);
                                    send(json);
                                    System.out.println("已送出" + json);
                                }catch (Exception e){
                                    log.error("錯誤發生",e);
                                }

                            }
                        }
                        , 0, 1000);
            }

            @Override
            public void onMessage(String s) {
                System.out.println("收到的訊息:" + s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                System.out.println("連線關閉");
            }

            @Override
            public void onError(Exception e) {
                System.out.println("連線錯誤" + e.getMessage());
            }
        };
        wsc.connect();
    }

    private static List<Double> generateECG(){
        List<Double> ecg = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ecg.add(Math.sin(i * 0.1) + ThreadLocalRandom.current().nextDouble(-0.05, 0.05));
        }
        return ecg;
    }
}
