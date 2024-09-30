package app.websocket.chat.entity;

import app.websocket.chat.ChatService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
public class Bang {
    @JsonProperty("ROOM_ID")
    private String ROOM_ID;
    @JsonProperty("ROOM_NAME")
    private String ROOM_NAME;
    private Set<WebSocketSession> sessions = new HashSet<>();


    @Builder //객체 생성에서 주입하는 것에 대한 방식 - Builder Pattern
    public Bang(String ROOM_ID, String ROOM_NAME) {
        this.ROOM_ID = ROOM_ID;
        this.ROOM_NAME = ROOM_NAME;
    }

}
