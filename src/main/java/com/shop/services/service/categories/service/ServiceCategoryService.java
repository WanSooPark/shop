package com.shop.services.service.categories.service;

import com.shop.models.categories.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceCategoryService {

    private final CategoryService categoryService;

}
