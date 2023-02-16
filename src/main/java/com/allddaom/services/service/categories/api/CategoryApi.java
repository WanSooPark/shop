package com.allddaom.services.service.categories.api;

import com.allddaom.services.service.categories.service.ServiceCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryApi {

    private final ServiceCategoryService serviceCategoryService;

}
