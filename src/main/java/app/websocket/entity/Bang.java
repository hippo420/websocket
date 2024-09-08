package app.websocket.entity;

import app.websocket.ChatService;
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
    public void handlerActions(WebSocketSession session, Message chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(Message.MessageType.ENTER)) {
            //방에 처음 들어왔을때
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }

        sendMessage(chatMessage, chatService);
        //메세지 전송
    }
    @Builder //객체 생성에서 주입하는 것에 대한 방식 - Builder Pattern
    public Bang(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }
    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
        //채팅방에 입장해 있는 모든 클라이언트에게 메세지 전송
    }
}
