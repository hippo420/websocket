package app.websocket.member;

import app.websocket.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberRepository {
    Member findMemberById(String userId);
}
