package app.websocket.member;

import app.websocket.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService service;

    @RequestMapping("/login")
    public Member login(@RequestBody Member member) {
        log.info("member: {}", member.toString());
       return service.prcLogin(member);
    }

    @PostMapping("/logout")
    public void logout(@ModelAttribute Member member) {

    }

    @PostMapping("/regist")
    public void regist(@RequestParam Member member) {

    }

    @PostMapping("/unregist")
    public void unregist(@RequestParam Member member) {

    }

    @GetMapping("/findMember")
    public List<Member> findMember(@RequestParam Member member) {

        return new ArrayList<Member>();
    }
}
