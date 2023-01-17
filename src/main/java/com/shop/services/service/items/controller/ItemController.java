package com.shop.services.service.items.controller;

import com.shop.services.service.items.dto.search.ServiceItemSearch;
import com.shop.services.service.items.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ServiceItemService serviceItemService;

    @GetMapping
    public String itemListView(@Valid ServiceItemSearch searchDto, Model model) {
        ServiceItemSearch.Response response = serviceItemService.search(searchDto);
        model.addAttribute("itemPage", response.getItemPage());
        return "item/item_list";
    }

}
