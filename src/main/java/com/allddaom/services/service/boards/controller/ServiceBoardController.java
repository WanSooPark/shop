package com.allddaom.services.service.boards.controller;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.boards.service.ServiceBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class ServiceBoardController {

    private final ServiceBoardService serviceBoardService;

    @GetMapping
    public String boardList(@CurrentAccount Member member, Model model) {
        return "mypage/board";
    }

}
