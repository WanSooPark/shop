package com.shop.services.admin.items.controller;

import com.shop.services.admin.items.dto.AdminItemDto;
import com.shop.services.admin.items.service.AdminItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/item")
@RequiredArgsConstructor
public class AdminItemController {

    private final AdminItemService adminItemService;

    @GetMapping
    public String itemListView(@PageableDefault Pageable pageable, Model model) {
        return "admin/item/product_product";
    }

    @GetMapping("/{id}")
    public String itemListView(@PathVariable Long id, Model model) {
        model.addAttribute("isNew", false);
        model.addAttribute("item", new AdminItemDto.Response(2L));
        return "admin/item/product_product_write";
    }

    @PostMapping
    public String newItem(HttpServletRequest request, Map parameter) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("");
        return "redirect:/admin/item";
    }

    @PutMapping("/{id}")
    public String updateItem(@PathVariable Long id, @RequestBody Map<String, Object> requestBody) {
        System.out.println();
        return "redirect:/admin/item";
    }

}
