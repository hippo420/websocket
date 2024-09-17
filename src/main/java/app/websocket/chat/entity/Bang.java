package app.websocket.chat.entity;

import app.websocket.chat.ChatService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
public class Bang {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();


    @Builder //객체 생성에서 주입하는 것에 대한 방식 - Builder Pattern
    public Bang(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

}
