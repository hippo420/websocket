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


        Member loginMember = memberRepository.findMemberById(member.getUserId());
        if(loginMember!= null)
        {
            log.info("loginMember [{}]",loginMember.toString());
            if(checkPassword(member.getPassword(), loginMember.getPassword())){
                return loginMember;
            }else{
                log.info("비번이 틀림");
                return null;
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
}
