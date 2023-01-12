package com.shop.commons.init;

import com.shop.models.categories.domain.Category;
import com.shop.models.categories.domain.CategoryStatus;
import com.shop.models.categories.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class AppStartRunner implements ApplicationRunner {

    private final CategoryService categoryService;

    @Value("${init_data}")
    private boolean initData;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (initData) { // properties 에 true 로 설정해두면 기본 데이터 초기화
            Category category1 = getCategory("분류1", null);
            Category category1_1 = getCategory("분류1-1", category1);
            Category category1_2 = getCategory("분류1-2", category1);
            Category category1_2_1 = getCategory("분류1-2-1", category1_2);
            Category category2 = getCategory("분류2", null);
            Category category2_1 = getCategory("분류2-1", category2);
            Category category2_2 = getCategory("분류2-2", category2);
            Category category2_1_1 = getCategory("분류2-1-1", category2_1);
            Category category2_1_2 = getCategory("분류2-1-2", category2_1);
        }
    }

    private Category getCategory(String name, Category topCategory) {
        Category category = categoryService.findByNameAndTopCategory(name, topCategory);
        if (ObjectUtils.isEmpty(category)) {
            category = new Category();
            category.setName(name);
            category.setTopCategory(topCategory);
            category.setStatus(CategoryStatus.EXPOSE);
            category = categoryService.add(category);
        }
        return category;
    }
}
