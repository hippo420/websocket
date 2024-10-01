package app.websocket.chat;

import app.websocket.chat.entity.Bang;
import app.websocket.chat.entity.ChatList;
import app.websocket.chat.entity.MSG;
import app.websocket.chat.entity.Message;
import app.websocket.member.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class ChatContoller {

    @Autowired
    private ChatService chatService;

    @RequestMapping("createRoom")
    public Bang createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
        //Post 요청이 들어올 시, Json에서 name 값을 받아 방을 생성한다.
    }

    @RequestMapping("/findMsgAll")
    public List<MSG> findMsgAll(@RequestParam String ROOM_ID) {
        log.info("findMsgAll roomId:{}", ROOM_ID);
        List<MSG> list = chatService.findMsgAll(ROOM_ID);
        log.info("msg {}건", list.size());
        for (MSG msg : list) {
            //log.info("msg:{}", msg.toString());
        }
        return list;
        //Get 요청이 들어올 시, 모든 방 목록을 조회한다.
    }

    @RequestMapping("/findBangByUserId")
    public List<ChatList> findBangByUserId(@RequestParam  String USER_ID) {
        log.info("findBangByUserId userId :{}",USER_ID);
        return chatService.findBangByUserId(USER_ID);
        //Get 요청이 들어올 시, 모든 방 목록을 조회한다.
    }

    @RequestMapping("sendMessage")
    public void sendMessage(HttpServletRequest request, @RequestParam String msg) {
         //chatService.sendMessage(,msg);
        //Get 요청이 들어올 시, 모든 방 목록을 조회한다.
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/messages/{roomId}")
    public Message chat( Message message) {
        log.info(message.toString());
        return chatService.sendMsg(message);
    }
}
