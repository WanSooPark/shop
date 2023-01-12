package com.shop.services.admin.categories.dto.form;

import com.shop.models.categories.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminCategoryForm {
    private Long id;
    private String name;
    private Long topCategoryId;

    public static AdminCategoryForm of(Category category) {
        return AdminCategoryForm.builder()
                .id(category.getId())
                .name(category.getName())
                .topCategoryId(category.getTopCategory()
                        .getId())
                .build();
    }
    public static AdminCategoryForm empty() {
        return AdminCategoryForm.builder()
                .build();
    }

    public CategoryBuilder entityBuilder() {
        return new DefaultCategoryBuilder(this);
    }

    public interface CategoryBuilder {
        CategoryBuilder topCategory(Category category);

        Category build();
    }

    public static class DefaultCategoryBuilder implements CategoryBuilder {
        private final Category category;
        private final AdminCategoryForm dto;

        public DefaultCategoryBuilder(AdminCategoryForm dto) {
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