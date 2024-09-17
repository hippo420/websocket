package app.websocket.system.config;

import app.websocket.chat.ChatService;
import app.websocket.chat.entity.Bang;
import app.websocket.chat.entity.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    @Autowired
    private final ChatService chatService;

    private final Map<String, Set<WebSocketSession>> roomSessions = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("handleTextMessage");
        String payload = message.getPayload();
        Message msg = objectMapper.readValue(payload, Message.class);
        Bang bang = getRoomId(msg.getROOM_ID());
        Set<WebSocketSession> sessions = bang.getSessions();
        // 해당 채팅방에 있는 모든 클라이언트에게 메시지 브로드캐스트

        if(Message.MessageType.ENTER.equals(msg.getTYPE())){
            sessions.add(session);
            msg.setMESSAGE(msg.getSND_ID()+"님이 참가했습니다.");
            sendMsg(sessions, new TextMessage(objectMapper.writeValueAsString(msg)));
        }else if(Message.MessageType.EXIT.equals(msg.getTYPE())){
            sessions.remove(session);
            msg.setMESSAGE(msg.getSND_ID()+"님이 퇴장했습니다.");
            sendMsg(sessions, new TextMessage(objectMapper.writeValueAsString(msg)));
        }else{
            sendMsg(sessions,message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("afterConnectionEstablished");
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("afterConnectionClosed");
    }

    private Bang getRoomId(String roomId) {
        // 요청 URI에서 방 ID를 추출
       return chatService.findRoomById(roomId);
    }

    private void sendMsg (Set<WebSocketSession> sessions, TextMessage message){
        sessions.parallelStream().forEach( session -> {
            try{
                session.sendMessage(message);
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        });
    }

}
