package com.shop.services.admin.categories.dto;

import com.shop.models.categories.domain.Category;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
public class AdminCategoryAddDto {
    private Long id;
    private String name;
    private Long topCategoryId;

    public CategoryBuilder entityBuilder() {
        return new DefaultCategoryBuilder(this);
    }

    public interface CategoryBuilder {
        CategoryBuilder topCategory(Category category);

        Category build();
    }

    public static class DefaultCategoryBuilder implements CategoryBuilder {
        private final Category category;
        private final AdminCategoryAddDto dto;

        public DefaultCategoryBuilder(AdminCategoryAddDto dto) {
            this.category = new Category();
            this.dto = dto;
        }

        @Override
        public CategoryBuilder topCategory(Category topCategory) {
            this.category.setTopCategory(topCategory);
            return this;
        }

        @Override
        public Category build() {
            this.category.setName(dto.getName());
            long depth = 1;
            Category topCategory = this.category.getTopCategory();
            if (!ObjectUtils.isEmpty(topCategory)) {
                depth = topCategory.getDepth() + 1;
            }
            this.category.setDepth(depth);
            return this.category;
        }
    }
}
