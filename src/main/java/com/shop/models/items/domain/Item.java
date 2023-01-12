package com.shop.models.items.domain;

import com.shop.commons.entity.BaseEntity;
import com.shop.models.badges.domain.Badge;
import com.shop.models.categories.domain.Category;
import com.shop.models.items.exception.OutOfStockException;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseEntity {

    /* 기본정보 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품 코드
    private String name; // 상품명
    private String englishName; // 영문 상품명
    private String basicDescription; // 상품 기본 설명
    private String manufacturer; // 제조사
    private String origin; // 원산지
    private String brand; // 브랜드
    private String barcode; // 바코드
    private String modelName; // 모델명
    private String keyword; // 검색어

    /* 판매정보 */
    @Enumerated(EnumType.STRING)
    private ItemSellStatus status; // 상품 상태 (승인 전, 판매중, 상품 노출, 품절, 판매금지)
    private Long regularPrice; // 정상가
    private Long salePrice; // 판매가
    @Enumerated(EnumType.STRING)
    private VatType vatType; // 상품과세 유형 (과세, 비과세)
    private Long fees; // 수수료
    private Long point; // 포인트
    private Long stock; // 재고
    private Long stockNotificationQuantity; // 통보 재고 수량
    private Long minimumPurchaseQuantity; // 최소 구매 수량
    private Long maximumPurchaseQuantity; // 최대 구매 가능 수량
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_x_item_option_builder",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "item_option_builder_id")
    )
    private List<ItemOptionBuilder> optionBuilders; // 옵션 빌더
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_x_item_option",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "item_option_id")
    )
    private List<ItemOption> options; // 옵션

    /* 상품정보 */
    @Enumerated(EnumType.STRING)
    private ProductProductionTimeType productionTimeType; // 상품 생산 시기 구분 (정상, 이월)
    private Integer productionYear; // 생산년도
    private Integer productionMonth; // 생산월
    private String seasonYear; // 시즌년도
    @Enumerated(EnumType.STRING)
    private Season season; // 시즌 (FW, SS)
    private String color; // 색상

    /* 이미지 */
    @OneToMany
    @JoinTable(
            name = "item_x_item_image",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "item_image_id")
    )
    private List<ItemImage> images;

    @Lob
    private String detailDescription; // 상품 상세 설명
    @Lob
    private String topDescription; // 상품 상단 내용
    @Lob
    private String bottomDescription; // 상품 하단 내용

    /* 상품정보고시 - 식품 */
    private String productName; // 제품명
    private String foodType; // 식품의 유형
    private String producerAndLocation; // 생산자 및 소재지
    private String qualityMaintenancePeriod; // 제조연월일, 소비기한 또는 품질유지기한
    private String quantityPerUnit; // 포장단위별 내용물의 용량(중량), 수량
    private String rawMaterials; // 원재료명 및 함량
    private String nutrient; // 영양성분
    private String geneticallyModifiedFood; // 유전자변형식품에 해당하는 경우의 표시
    private String safetyPrecautions; // 소비자안전을 위한 주의사항
    private String importedFoodStationery; // 수입식품 문구
    private String managerAndPhoneNumber; // 소비자상담관련 전화번호

    /* 분류 */
    @OneToMany
    @JoinTable(
            name = "item_x_badge",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "badge_id")
    )
    private List<Badge> badges; // 뱃지 (BEST, NEW)
    @ManyToOne
    private Category category; // 카테고리

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
