package com.shop.services.admin.items.dto.form;

import com.shop.models.items.domain.ItemOption;
import com.shop.models.items.domain.ItemOptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemOptionForm {
    private Long id;
    private String name;
    private Long price;
    private Long stock;
    private Long stockNotificationQuantity; // 통보 재고 수량
    private String status;

    public static ItemOptionForm of(ItemOption option) {
        return ItemOptionForm.builder()
                .id(option.getId())
                .name(option.getName())
                .price(option.getPrice())
                .stock(option.getStock())
                .stockNotificationQuantity(option.getStockNotificationQuantity())
                .status(option.getStatus()
                        .name())
                .build();
    }

    public ItemOptionBuilder entityBuilder() {
        return new DefaultItemOptionBuilder(this);
    }

    public interface ItemOptionBuilder {
        ItemOption build();
    }

    public static class DefaultItemOptionBuilder implements ItemOptionBuilder {
        private final ItemOption itemOption;
        private final ItemOptionForm dto;

        public DefaultItemOptionBuilder(ItemOptionForm dto) {
            this.itemOption = new ItemOption();
            this.dto = dto;
        }

        @Override
        public ItemOption build() {
            this.itemOption.setName(dto.getName());
            this.itemOption.setStock(dto.getStock());
            this.itemOption.setPrice(dto.getPrice());
            this.itemOption.setStockNotificationQuantity(dto.getStockNotificationQuantity());
            this.itemOption.setStatus(ItemOptionStatus.getStringToEnum(dto.getStatus()));

            return this.itemOption;
        }
    }

}
