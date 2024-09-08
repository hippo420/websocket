package app.websocket;

import app.websocket.entity.Bang;
import app.websocket.entity.Message;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatContoller {

    @Autowired
    private ChatService chatService;

    @RequestMapping("createRoom")
    public Bang createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
        //Post 요청이 들어올 시, Json에서 name 값을 받아 방을 생성한다.
    }

    @RequestMapping("findAllRoom")
    public List<Bang> findAllRoom() {
        return chatService.findAllRoom();
        //Get 요청이 들어올 시, 모든 방 목록을 조회한다.
    }

    @RequestMapping("sendMessage")
    public void sendMessage(HttpServletRequest request, @RequestParam String msg) {
         //chatService.sendMessage(,msg);
        //Get 요청이 들어올 시, 모든 방 목록을 조회한다.
    }
}
