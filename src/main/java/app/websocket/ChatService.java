package app.websocket;

import app.websocket.entity.Bang;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;
@Slf4j
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, Bang> chatRooms;

    public ChatService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    //roomId를 key로 가지고 chatRoom을 value로 가짐

    @PostConstruct //Bean 의존성 주입이 완료되고 실행되어야 하는 메서드에 사용
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //모든 방을 찾는 메서드
    public List<Bang> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    //id로 방을 찾고 결과로 ChatRoom 객체 반환
    public Bang findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    //방 생성 메서드
    public Bang createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        //랜덤 roomId 생성
        Bang chatRoom = Bang.builder() //builder로 변수 세팅
                .roomId(randomId)
                .name(name)
                .build();

        chatRooms.put(randomId, chatRoom); //방 생성 후 방 목록에 추가
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
