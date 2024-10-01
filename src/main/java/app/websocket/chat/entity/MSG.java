package app.websocket.chat.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MSG {

    //단순 DTO
    @JsonProperty("TYPE")
    private String TYPE;
    @JsonProperty("ROOM_ID")
    private String ROOM_ID;
    @JsonProperty("SND_ID")
    private String SND_ID;
    @JsonProperty("MESSAGE")
    private String MESSAGE;
    @JsonProperty("SND_DTM")
    private String SND_DTM;
    @JsonProperty("IMG_CTT")
    private Byte[] IMG_CTT;




}
