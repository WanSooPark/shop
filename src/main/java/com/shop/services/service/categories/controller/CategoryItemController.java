package com.shop.services.service.categories.controller;

import com.shop.services.service.categories.dto.ServiceCategoryResponse;
import com.shop.services.service.categories.dto.search.ServiceCategoryItemSearchDto;
import com.shop.services.service.categories.service.ServiceCategoryItemService;
import com.shop.services.service.categories.service.ServiceCategoryService;
import com.shop.services.service.items.dto.category.ServiceCategorySideMenuResponse;
import com.shop.services.service.items.dto.search.ServiceItemSearchDto;
import com.shop.services.service.items.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/category/{categoryId}/item")
@RequiredArgsConstructor
public class CategoryItemController {

    private final ServiceCategoryItemService serviceCategoryItemService;

    private final ServiceCategoryService serviceCategoryService;

    @GetMapping
    public String itemListView(@PathVariable Long categoryId, @Valid ServiceCategoryItemSearchDto.Request searchDto, @PageableDefault Pageable pageable, Model model) {
        ServiceCategoryItemSearchDto.Response response = serviceCategoryItemService.search(categoryId, searchDto, pageable);
        ServiceCategoryResponse serviceCategoryResponse = serviceCategoryService.getCategory(categoryId);
        ServiceCategorySideMenuResponse serviceCategorySideMenuResponse = serviceCategoryService.getSideMenu(categoryId);

        model.addAttribute("itemPage", response.getItemPage());
        model.addAttribute("category", serviceCategoryResponse);
        model.addAttribute("categorySizeMenu", serviceCategorySideMenuResponse);
        return "item/category/item_list";
    }

}