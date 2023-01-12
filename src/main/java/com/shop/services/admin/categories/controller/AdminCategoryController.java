package com.shop.services.admin.categories.controller;

import com.shop.commons.errors.exceptions.BusinessException;
import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.services.admin.categories.dto.AdminCategoryResponse;
import com.shop.services.admin.categories.dto.AdminCategorySearchDto;
import com.shop.services.admin.categories.dto.form.AdminCategoryForm;
import com.shop.services.admin.categories.service.AdminCategoryService;
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
import java.util.List;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public String categoryList(@Valid AdminCategorySearchDto.Request searchDto, @PageableDefault(size = 5) Pageable pageable, Model model) {
        AdminCategorySearchDto.Response response = adminCategoryService.search(searchDto, pageable);
        model.addAttribute("categoriesPage", response.getCategoriesPage());
        return "admin/category/product_category";
    }

    @GetMapping("/form")
    public String categoryForm(@RequestParam(required = false) Long id, Model model) {
        boolean isNew = true;

        AdminCategoryForm category = AdminCategoryForm.empty();
        if (!ObjectUtils.isEmpty(id)) {
            try {
                category = adminCategoryService.getCategoryForm(id);
                isNew = false;
            } catch (NoContentException noContentException) {
            }
        }
        model.addAttribute("category", category);

        List<AdminCategoryResponse> topCategories = adminCategoryService.findTopCategories();
        model.addAttribute("topCategories", topCategories);

        model.addAttribute("isNew", isNew);
        return "admin/category/product_category_write";
    }

    @PostMapping
    public String newCategory(@Valid AdminCategoryForm dto, RedirectAttributes attributes) {
        try {
            AdminCategoryResponse response;
            if (dto.getId() == 0) {
                response = adminCategoryService.add(dto);
            } else {
                response = adminCategoryService.update(dto);
            }
        } catch (
                BusinessException businessException) {
            attributes.addAttribute("message", businessException.getMessage());
            attributes.addAttribute("exception", businessException);
            return "admin/item/product_category_write";
        }
        return "redirect:/admin/category";
    }

}
