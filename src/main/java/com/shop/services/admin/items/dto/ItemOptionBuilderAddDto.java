package com.shop.services.admin.items.dto;

import com.shop.models.items.domain.ItemOptionBuilder;
import lombok.Data;

@Data
public class ItemOptionBuilderAddDto {
    private String name; // 옵션 빌더 명
    private String value; // 옵션 항목 값

    public ItemOptionBuilderBuilder entityBuilder() {
        return new DefaultItemOptionBuilderBuilder(this);
    }

    public interface ItemOptionBuilderBuilder {
        ItemOptionBuilder build();
    }

    public static class DefaultItemOptionBuilderBuilder implements ItemOptionBuilderBuilder {
        private final ItemOptionBuilder itemOptionBuilder;
        private final ItemOptionBuilderAddDto dto;

        public DefaultItemOptionBuilderBuilder(ItemOptionBuilderAddDto dto) {
            this.itemOptionBuilder = new ItemOptionBuilder();
            this.dto = dto;
        }

        @Override
        public ItemOptionBuilder build() {
            this.itemOptionBuilder.setName(dto.getName());
            this.itemOptionBuilder.setValue(dto.getValue());
            return this.itemOptionBuilder;
        }
    }
}
