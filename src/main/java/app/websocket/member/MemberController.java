package app.websocket.member;

import app.websocket.member.entity.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService service;

    @RequestMapping("/login")
    public Member login(HttpServletResponse response, @RequestBody Member member) {
        log.info("member: {}", member.toString());
        Cookie cookie = new Cookie("token", String.valueOf(UUID.randomUUID()));
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
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

    @RequestMapping("/findFriend")
    public List<Member> findFirend(@RequestParam String USER_ID) {
        log.info("member: {}", USER_ID);
        List<Member> friendList = service.findFriend(USER_ID);
        return friendList;
    }
}
