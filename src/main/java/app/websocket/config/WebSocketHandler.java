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

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper jsonMapper;
    private final ChatService chatService;

    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("handleTextMessage");
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(message);
                log.info("sendMessage => [{}]->[{}]", webSocketSession.getId(),message);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("afterConnectionEstablished");

        sessions.add(session);
        log.info("[{}] session Opened? {}",session.getId(),session.isOpen());

        Iterator<WebSocketSession> iterator = sessions.iterator();
        log.info("session List ");
        while (iterator.hasNext()) {
            WebSocketSession webSocketSession = iterator.next();
            if (webSocketSession.isOpen()) {
                log.info("sessionID = {} ",webSocketSession.getId());
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("afterConnectionClosed");

        sessions.remove(session);
    }

}
