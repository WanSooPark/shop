package com.shop.services.service.login.controller;

import com.shop.models.members.domain.Member;
import com.shop.services.service.login.dto.LoginInfoReq;
import com.shop.services.service.login.service.LoginService;
import com.shop.services.service.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

//    @PostMapping("/login")
//    @ResponseBody
//    public ResponseEntity<Object> loginProcess(@Valid LoginInfoReq loginInfoReq, Errors errors, HttpSession session) {
//        if (errors.hasErrors()) {
//            ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body("입력값이 올바르지 않습니다.");
//        }
//
//        try {
//            Member member = loginService.loginProcess(loginInfoReq, session);
//            return ResponseEntity.ok()
//                    .build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body(e.getMessage());
//        }
//    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
