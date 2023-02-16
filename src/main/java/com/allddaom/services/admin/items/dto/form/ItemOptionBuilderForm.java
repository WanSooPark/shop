package com.allddaom.services.admin.items.dto.form;

import com.allddaom.models.items.domain.ItemOptionBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemOptionBuilderForm {
    private Long id; // 옵션 빌더 명
    private String name; // 옵션 빌더 명
    private String value; // 옵션 항목 값

    public static ItemOptionBuilderForm of(ItemOptionBuilder optionBuilder) {
        return ItemOptionBuilderForm.builder()
                .id(optionBuilder.getId())
                .name(optionBuilder.getName())
                .value(optionBuilder.getValue())
                .build();
    }

    public ItemOptionBuilderBuilder entityBuilder() {
        return new DefaultItemOptionBuilderBuilder(this);
    }

    public interface ItemOptionBuilderBuilder {
        ItemOptionBuilder build();
    }

    public static class DefaultItemOptionBuilderBuilder implements ItemOptionBuilderBuilder {
        private final ItemOptionBuilder itemOptionBuilder;
        private final ItemOptionBuilderForm dto;

        public DefaultItemOptionBuilderBuilder(ItemOptionBuilderForm dto) {
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
