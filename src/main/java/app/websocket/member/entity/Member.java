package app.websocket.member.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Member {
    @JsonProperty("USER_ID")
    private String USER_ID;
    @JsonProperty("PASSWORD")
    private String PASSWORD;
    @JsonProperty("USER_NAME")
    private String USER_NAME;
    @JsonProperty("IMG_CTT")
    private Byte[] IMG_CTT;
    @JsonProperty("COMMENT")
    private String COMMENT;

    @Override
    public String toString() {
        return "Member{" +
                "USER_ID='" + USER_ID + '\'' +
                ", password='" + PASSWORD + '\'' +
                '}';
    }
}
