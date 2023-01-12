package com.shop.services.admin.items.controller;

import com.shop.commons.errors.exceptions.BusinessException;
import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.services.admin.categories.dto.AdminCategoryResponse;
import com.shop.services.admin.items.dto.AdminItemAddDto;
import com.shop.services.admin.items.dto.AdminItemResponse;
import com.shop.services.admin.items.dto.AdminItemSearchDto;
import com.shop.services.admin.items.dto.AdminItemUpdateDto;
import com.shop.services.admin.items.dto.form.AdminItemForm;
import com.shop.services.admin.items.service.AdminItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/item")
@RequiredArgsConstructor
public class AdminItemController {

    private final AdminItemService adminItemService;

    @GetMapping
    public String itemListView(@Valid AdminItemSearchDto.Request searchDto, @PageableDefault Pageable pageable, Model model) {
        AdminItemSearchDto.Response response = adminItemService.search(searchDto, pageable);
        model.addAttribute("items", response.getItems());
        return "admin/item/product_product";
    }

    @GetMapping("/form")
    public String itemListView(@RequestParam(required = false) Long id, Model model) {
        boolean isNew = true;

        AdminItemForm form = new AdminItemForm();
        if (!ObjectUtils.isEmpty(id)) {
            try {
                AdminItemResponse item = adminItemService.getItem(id);
                isNew = false;
            } catch (NoContentException noContentException) {
            }
        }

        model.addAttribute("year", LocalDateTime.now().getYear());
        model.addAttribute("item", form);
        model.addAttribute("isNew", isNew);
        return "admin/item/product_product_write";
    }

    @PostMapping
    public String newItem(@Valid AdminItemAddDto dto, RedirectAttributes attributes) {
        try {
            AdminItemResponse response = adminItemService.addItem(dto);
        } catch (BusinessException businessException) {
            attributes.addAttribute("message", businessException.getMessage());
            attributes.addAttribute("exception", businessException);
            return "admin/item/product_product_write";
        }
        return "redirect:/admin/item";
    }

    @PutMapping("/{id}")
    public String updateItem(@PathVariable Long id, @Valid AdminItemUpdateDto dto, RedirectAttributes attributes) {
        try {
            AdminItemResponse response = adminItemService.updateItem(id, dto);
        } catch (BusinessException businessException) {
            attributes.addAttribute("message", businessException.getMessage());
            attributes.addAttribute("exception", businessException);
            return "admin/item/product_product_write";
        }
        return "redirect:/admin/item";
    }

}
