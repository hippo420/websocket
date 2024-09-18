package app.websocket.chat.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ChatList {
    private String USER_ID;
    private String ROOM_ID;
    private String NAME;
    private Byte[] IMG_CTT;
    private String MESSAGE;
    private String SND_DTM;
}
