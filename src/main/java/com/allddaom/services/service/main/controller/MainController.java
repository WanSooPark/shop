package com.allddaom.services.service.main.controller;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.items.dto.search.ServiceItemSearchDto;
import com.allddaom.services.service.main.dto.banner.MainBannerResponse;
import com.allddaom.services.service.main.dto.cateogry.MainCategoryResponse;
import com.allddaom.services.service.main.dto.topic.MainTopicResponse;
import com.allddaom.services.service.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/")
    public String main(@CurrentAccount Member member, ServiceItemSearchDto serviceItemSearch, @PageableDefault Pageable pageable, Model model) {
        List<MainBannerResponse> banners = mainService.findBanners();
        model.addAttribute("banners", banners);

        MainTopicResponse todaySaleTopic = mainService.findTopicItemsByTopicCode("TODAY_SALE", member); // 하루특가
        model.addAttribute("todaySaleTopic", todaySaleTopic);

        MainTopicResponse showMainTopic = mainService.findShowMainTopicItems(member);
        model.addAttribute("showMainTopic", showMainTopic);

        List<MainCategoryResponse> recItemsCategories = mainService.recItemsCategories(member);
        model.addAttribute("recItemsCategories", recItemsCategories);

        MainTopicResponse newTopic = mainService.findTopicItemsByTopicCode("NEW", member); // 신상품
        model.addAttribute("newTopic", newTopic);

        MainCategoryResponse hotItemItemsCategory = mainService.hotItemItemsCategory(member);
        model.addAttribute("hotItemItemsCategory", hotItemItemsCategory);

        MainTopicResponse mdRecTopic = mainService.findTopicItemsByTopicCode("MD_REC", member); // MD 추천 기획전
        model.addAttribute("mdRecTopic", mdRecTopic);

        MainTopicResponse timeSaleTopic = mainService.findTopicItemsByTopicCode("TIME_SALE", member); // 타임특가
        model.addAttribute("timeSaleTopic", timeSaleTopic);

        return "main";
    }

}