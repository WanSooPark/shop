package com.shop.models.items.domain;

import com.shop.commons.entity.BaseEntity;
import com.shop.models.items.exception.OutOfStockException;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품 코드

    @Column(nullable = false, length = 50)
    private String name; // 상품명

    @Column(nullable = false)
    private Long price; // 가격

    @Column(nullable = false)
    private Long stock; // 재고

    @Lob
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus status; //상품 판매 상태

    /**
     * 출고
     */
    public void release(long stock) {
        long restStock = this.stock - stock;
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stock + ")");
        }
        this.stock = restStock;
    }

    /**
     * 입고
     */
    public void warehousing(long stock) {
        this.stock += stock;
    }

}
