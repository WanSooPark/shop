package com.shop.services.admin.categories.controller;

import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.services.admin.categories.dto.AdminCategoryAddDto;
import com.shop.services.admin.categories.dto.AdminCategoryResponse;
import com.shop.services.admin.categories.dto.AdminCategorySearchDto;
import com.shop.services.admin.categories.service.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public String categoryList(@Valid AdminCategorySearchDto.Request searchDto, @PageableDefault Pageable pageable, Model model) {
        AdminCategorySearchDto.Response response = adminCategoryService.search(searchDto, pageable);
        model.addAttribute("categoriesPage", response.getCategoriesPage());
        return "admin/category/product_category";
    }

    @GetMapping("/form")
    public String categoryForm(@RequestParam(required = false) Long id, Model model) {
        boolean isNew = true;

        AdminCategoryResponse category = AdminCategoryResponse.empty();
        if (!ObjectUtils.isEmpty(id)) {
            try {
                category = adminCategoryService.getCategory(id);
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
    public String newCategory(@Valid AdminCategoryAddDto addDto) {
        AdminCategoryResponse response = adminCategoryService.add(addDto);
        return "redirect:/admin/category";
    }

}
