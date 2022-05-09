package com.teamY.simple.simplyChat.controller;

import com.teamY.simple.simplyChat.dto.JoinDTO;
import com.teamY.simple.simplyChat.service.MemberService;
import com.teamY.simple.simplyChat.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberService memberService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("main")
    public String mainPage() {
        return "main";
    }
    @GetMapping("/joinPage")
    public String joinPage(Model model) {
        JoinDTO joinDTO = new JoinDTO();
        model.addAttribute("joinDTO", joinDTO);
        return "join";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("joinDTO") JoinDTO joinDTO) {

        log.info("joinDTO : " + joinDTO.toString());

        String encodePassword = bCryptPasswordEncoder.encode(joinDTO.getPassword());
        MemberVO memberVO = new MemberVO(joinDTO.getNickname(), encodePassword);

        log.info("memberVO : " + memberVO.toString());
        memberService.registerMember(memberVO);
        return "redirect:/hello";
    }
}
