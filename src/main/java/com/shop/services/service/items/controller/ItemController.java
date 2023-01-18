package com.shop.services.service.items.controller;

import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.services.service.categories.dto.ServiceCategoryResponse;
import com.shop.services.service.categories.service.ServiceCategoryService;
import com.shop.services.service.items.dto.ServiceItemResponse;
import com.shop.services.service.items.dto.category.ServiceCategorySideMenuResponse;
import com.shop.services.service.items.dto.search.ServiceItemSearch;
import com.shop.services.service.items.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ServiceItemService serviceItemService;
    private final ServiceCategoryService serviceCategoryService;

    @GetMapping
    public String itemListView(@Valid ServiceItemSearch.Request searchDto, @PageableDefault Pageable pageable, Model model) {
        ServiceItemSearch.Response response = serviceItemService.search(searchDto, pageable);
        ServiceCategoryResponse serviceCategoryResponse = serviceCategoryService.getCategory(searchDto.getCategoryId());
        ServiceCategorySideMenuResponse serviceCategorySideMenuResponse = serviceCategoryService.getSideMenu(searchDto.getCategoryId());

        model.addAttribute("itemPage", response.getItemPage());
        model.addAttribute("category", serviceCategoryResponse);
        model.addAttribute("categorySizeMenu", serviceCategorySideMenuResponse);
        return "item/item_list";
    }

    @GetMapping("/{id}")
    public String itemDetailView(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            ServiceItemResponse item = serviceItemService.getItem(id);
            boolean b = item.getBrand() != null && item.getBrand() != "";
            model.addAttribute("item", item);
        } catch (NoContentException noContentException) {
            redirectAttributes.addFlashAttribute("message", "유효하지 않은 상품 id 입니다.");
            return "redirect:/";
        }
        model.addAttribute("isZzim", true);
        model.addAttribute("countOfReview", 10);
        model.addAttribute("countOfQNA", 20);
        return "item/item_detail";
    }

}
