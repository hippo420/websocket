package app.websocket.chat;

import app.websocket.chat.entity.Bang;
import app.websocket.chat.entity.ChatList;
import app.websocket.chat.entity.Message;
import app.websocket.system.utils.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ChatRepository  chatRepository;

    public ChatService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    //roomId를 key로 가지고 chatRoom을 value로 가짐

    @PostConstruct //Bean 의존성 주입이 완료되고 실행되어야 하는 메서드에 사용
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //모든 방을 찾는 메서드
    public List<ChatList> findBangByUserId(String userId) {
        return chatRepository.findBangByUserId(userId);
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
                .ROOM_ID(randomId)
                .ROOM_NAME(name)
                .build();

        chatRooms.put(randomId, chatRoom); //방 생성 후 방 목록에 추가
        return chatRoom;
    }

    public Message sendMsg(Message message){
        message.setSND_DTM(DateUtil.getDTM());
        message.setTYPE(Message.MessageType.TALK);
        chatRepository.saveMessage(message);
        return Message.builder()
                .TYPE(message.getTYPE())
                .MESSAGE(message.getMESSAGE())
                .ROOM_ID(message.getROOM_ID())
                .SND_ID(message.getSND_ID())
                .SND_DTM(message.getSND_DTM())
                .build();
    }

    public List<Message> findMsgAll(String roomId) {
        return chatRepository.findMsgAll(roomId);
    }
}
