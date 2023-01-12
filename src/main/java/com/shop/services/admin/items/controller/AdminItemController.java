package com.shop.services.admin.items.controller;

import com.shop.commons.errors.exceptions.BusinessException;
import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.services.admin.categories.dto.AdminCategoryResponse;
import com.shop.services.admin.categories.service.AdminCategoryService;
import com.shop.services.admin.items.dto.AdminItemResponse;
import com.shop.services.admin.items.dto.form.AdminItemForm;
import com.shop.services.admin.items.dto.search.AdminItemSearchDto;
import com.shop.services.admin.items.service.AdminItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/item")
@RequiredArgsConstructor
public class AdminItemController {

    private final AdminItemService adminItemService;
    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public String itemListView(@Valid AdminItemSearchDto.Request searchDto, @PageableDefault Pageable pageable, Model model) {
        AdminItemSearchDto.Response response = adminItemService.search(searchDto, pageable);
        model.addAttribute("itemPage", response.getItemPage());
        return "admin/item/product_product";
    }

    @GetMapping("/form")
    public String itemListView(@RequestParam(required = false) Long id, Model model) {
        boolean isNew = true;

//        AdminItemForm form = AdminItemForm.empty();
        AdminItemForm form = new AdminItemForm();
        if (!ObjectUtils.isEmpty(id)) {
            try {
                form = adminItemService.getItemForm(id);
                isNew = false;
            } catch (NoContentException noContentException) {
            }
        }

        model.addAttribute("year", LocalDateTime.now()
                .getYear());
        model.addAttribute("item", form);
        List<AdminCategoryResponse> categories = adminCategoryService.findAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("isNew", isNew);
        return "admin/item/product_product_write";
    }

    @PostMapping
    public String newItem(@Valid AdminItemForm dto, RedirectAttributes attributes) {
        try {
            AdminItemResponse response;
            if (dto.getId() == 0) {
                response = adminItemService.addItem(dto);
            } else {
                response = adminItemService.updateItem(dto);
            }
        } catch (BusinessException businessException) {
            attributes.addAttribute("message", businessException.getMessage());
            attributes.addAttribute("exception", businessException);
            return "admin/item/product_product_write";
        }
        return "redirect:/admin/item";
    }

}
