package com.shop.services.service.main.controller;

import com.shop.models.items.service.ItemService;
import com.shop.services.service.items.dto.search.ServiceItemSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @GetMapping("/")
    public String main(ServiceItemSearch serviceItemSearch, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
//        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

//        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", serviceItemSearch);
        model.addAttribute("maxPage", 5);

        return "main";
    }

}