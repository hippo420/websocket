package app.websocket.member.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Member {
    private String userId;
    private String password;

    @Override
    public String toString() {
        return "Member{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
