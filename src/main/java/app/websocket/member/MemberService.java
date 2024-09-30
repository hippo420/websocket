package app.websocket.member;

import app.websocket.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    public Member prcLogin(Member member){

        if(member.getUSER_ID() == null)
            throw new RuntimeException("ID is null");

        Member loginMember = memberRepository.findMemberById(member.getUSER_ID());
        if(loginMember!= null)
        {
            log.info("loginMember [{}]",loginMember.toString());
            if(checkPassword(member.getPASSWORD(), loginMember.getPASSWORD())){
                return loginMember;
            }else{
                log.info("비번이 틀림");
                throw new RuntimeException("비번이 틀림");
            }
        }

        return null;
    }

    public Member findById(String id){
        return new Member();
    }

    public List<Member> findByLikeName(String name){
        return new ArrayList<Member>();
    }

    public void registerMember(Member member){

    }

    public void unregisterMember(Member member){

    }

    public boolean checkPassword(String inputdata, String DBpassword){
        if(!inputdata.equals(DBpassword))
            return false;
        return true;
    }

    public List<Member> findFriend(String userId) {
        return memberRepository.findFriend(userId);
    }
}
