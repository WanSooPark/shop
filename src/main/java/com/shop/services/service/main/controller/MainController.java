package com.shop.services.service.main.controller;

import com.shop.services.service.items.dto.search.ServiceItemSearch;
import com.shop.services.service.main.dto.badge.MainNewBadgeItemResponse;
import com.shop.services.service.main.service.MainService;
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
    public String main(ServiceItemSearch serviceItemSearch, @PageableDefault Pageable pageable, Model model) {
        List<MainNewBadgeItemResponse> newItems = mainService.findNewItems();
        model.addAttribute("newItems", serviceItemSearch);
        model.addAttribute("maxPage", 5);

        return "main";
    }

}