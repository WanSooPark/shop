package com.shop.services.service.main.controller;

import com.shop.commons.security.CurrentAccount;
import com.shop.models.members.domain.Member;
import com.shop.services.service.items.dto.search.ServiceItemSearchDto;
import com.shop.services.service.main.dto.topic.MainTopicResponse;
import com.shop.services.service.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/")
    public String main(@CurrentAccount Member member, ServiceItemSearchDto serviceItemSearch, @PageableDefault Pageable pageable, Model model) {
        MainTopicResponse todaySaleTopic = mainService.findTodaySaleTopicItems(member); // 하루특가
        model.addAttribute("todaySaleTopic", todaySaleTopic);

        MainTopicResponse showMainTopic = mainService.findShowMainTopicItems(member);
        model.addAttribute("showMainTopic", showMainTopic);

        return "main";
    }

}