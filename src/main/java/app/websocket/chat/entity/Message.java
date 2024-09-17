package app.websocket.chat.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
public class Message {
    public enum MessageType{
        ENTER, TALK, EXIT
        //처음 입장인지 아닌지 구별하는 Enum
    }
    //단순 DTO

    private MessageType TYPE;
    private String ROOM_ID;
    private String SND_ID;
    private String MESSAGE;
    private String SND_DTM;

    @Builder
    public Message(String ROOM_ID, String SND_ID, String MESSAGE,String SND_DTM, MessageType TYPE) {
        this.ROOM_ID = ROOM_ID;
        this.SND_ID = SND_ID;
        this.TYPE = TYPE;
        this.MESSAGE = MESSAGE;
        this.SND_DTM = SND_DTM;
    }


}
