package app.websocket.config;

import app.websocket.ChatService;
import app.websocket.entity.Bang;
import app.websocket.entity.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    //private final ObjectMapper jsonMapper;
    //private final ChatService chatService;

    private final Map<String, Set<WebSocketSession>> roomSessions = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("handleTextMessage");
        String roomId = getRoomId(session);
        Set<WebSocketSession> sessions = roomSessions.get(roomId);
        // 해당 채팅방에 있는 모든 클라이언트에게 메시지 브로드캐스트

        if (sessions != null) {
            log.info("{}방 sessions 개수 {}",roomId,sessions.size());
            for (WebSocketSession webSocketSession : sessions) {
                if (webSocketSession.isOpen()) {
                    webSocketSession.sendMessage(message);
                }
            }
        }else{
            log.info("{}방 sessions 개수 {}",roomId,0);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("afterConnectionEstablished");
        String room = getRoomId(session); // 요청에서 방 ID를 가져옴
        roomSessions.computeIfAbsent(room, k -> Collections.synchronizedSet(new HashSet<>())).add(session);

    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("afterConnectionClosed");
        String roomId = getRoomId(session); // 요청에서 방 ID를 가져옴
        Set<WebSocketSession> sessions = roomSessions.get(roomId);
        if (sessions != null) {
            sessions.remove(session);
        }
    }

    private String getRoomId(WebSocketSession session) {
        // 요청 URI에서 방 ID를 추출
        log.info("getRoomId: {}",session.getUri().getPath().split("/")[3]);
        return session.getUri().getPath().split("/")[3]; // 예: /chat/room1 이라면 "room1"을 가져옴
    }

}
