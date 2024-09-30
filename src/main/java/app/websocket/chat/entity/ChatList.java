package app.websocket.chat.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ChatList {
    @JsonProperty("USER_ID")
    private String USER_ID;

    @JsonProperty("ROOM_ID")
    private String ROOM_ID;

    @JsonProperty("ROOM_NAME")
    private String ROOM_NAME;

    @JsonProperty("IMG_CTT")
    private Byte[] IMG_CTT;

    @JsonProperty("MESSAGE")
    private String MESSAGE;

    @JsonProperty("SND_DTM")
    private String SND_DTM;

    @JsonProperty("ROOM_TYCD")
    private String ROOM_TYCD;

    @JsonProperty("JOIN_CNT")
    private String JOIN_CNT;

}
