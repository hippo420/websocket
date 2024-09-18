package app.websocket.chat;

import app.websocket.chat.entity.Bang;
import app.websocket.chat.entity.ChatList;
import app.websocket.chat.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ChatRepository {
    List<ChatList> findBangByUserId(String userId);

    void saveMessage(Message message);

    List<Message> findMsgAll(String userId);

}
