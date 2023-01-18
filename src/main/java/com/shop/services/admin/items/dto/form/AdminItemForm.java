package com.shop.services.admin.items.dto.form;

import com.shop.models.badges.domain.Badge;
import com.shop.models.categories.domain.Category;
import com.shop.models.items.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminItemForm {
    private String method; // post, put
    private Long id = 0L; // 상품 코드
    private String name; // 상품명
    private String englishName = "englishName"; // 영문 상품명
    private String basicDescription = "basicDescription"; // 상품 기본 설명
    private String manufacturer = "manufacturer"; // 제조사
    private String origin = "origin"; // 원산지
    private String brand = "brand"; // 브랜드
    private String barcode = "barcode"; // 바코드
    private String modelName = "modelName"; // 모델명
    private String keyword = "keyword"; // 검색어
    /* 판매정보 */
    private boolean beforeApprovalStatus; // 승인 전
    private boolean saleStatus = true; // 판매 중
    private boolean productExposureStatus = true; // 상품 노출
    private boolean soldOutStatus; // 품절
    private boolean noSaleStatus; // 판매 금지

    private Long regularPrice = 0L; // 정상가
    private Long salePrice = 0L; // 판매가
    private String vatType = "UNUSED"; // 상품과세 유형 (과세, 비과세)
    private Long fees = 0L; // 수수료
    private String pointType; // 포인트 유형 disabled
    private Long point = 0L; // 포인트
    private Long stock = 0L; // 재고
    private Long stockNotificationQuantity = 0L; // 통보 재고 수량
    private Long minimumPurchaseQuantity = 0L; // 최소 구매 수량
    private Long maximumPurchaseQuantity = 0L; // 최대 구매 가능 수량
    private List<ItemOptionBuilderForm> optionBuilders; // 옵션 빌더
    private List<ItemOptionForm> options; // 옵션
    /* 상품정보 */
    private String productionTimeType = "CARRY_OVER"; // ProductProductionTimeType 상품 생산 시기 구분 NORMAL("정상"), CARRY_OVER("이월");
    private Integer productionYear = 2023; // 생산년도
    private Integer manufacturingYear = 2023; // 제조일 년도
    private Integer manufacturingMonth = 5; // 제조일 월
    private String seasonYear = "2023"; // 시즌년도
    private String season = "FW"; // 시즌 (FW, SS) Season
    private String color; // 색상

    /* 이미지 */
    private List<MultipartFile> imageFiles; // 이미지 파일 목록

    @Lob
    private String detailDescription; // 상품 상세 설명
    @Lob
    private String topDescription; // 상품 상단 내용
    @Lob
    private String bottomDescription; // 상품 하단 내용
    /* 상품정보고시 */
    private String type = "food"; // 상품 타입
    private String productName = "상세설명참조"; // 제품명
    private String foodType = "상세설명참조"; // 식품의 유형
    private String producerAndLocation = "상세설명참조"; // 생산자 및 소재지
    private String qualityMaintenancePeriod = "상세설명참조"; // 제조연월일, 소비기한 또는 품질유지기한
    private String quantityPerUnit = "상세설명참조"; // 포장단위별 내용물의 용량(중량), 수량
    private String rawMaterials = "상세설명참조"; // 원재료명 및 함량
    private String nutrient = "상세설명참조"; // 영양성분
    private String geneticallyModifiedFood = "상세설명참조"; // 유전자변형식품에 해당하는 경우의 표시
    private String safetyPrecautions = "상세설명참조"; // 소비자안전을 위한 주의사항
    private String importedFoodStationery = "상세설명참조"; // 수입식품 문구
    private String managerAndPhoneNumber = "상세설명참조"; // 소비자상담관련 전화번호
    /* 분류 */
    private List<String> badges; // 뱃지 (BEST, NEW)
    private boolean bestBadge;
    private boolean newBadge = true;
    private Long categoryId; // 카테고리 객체

    /* enum 바인딩 */
//    public ItemSellStatus getStatus() {
//        return ItemSellStatus.getStringToEnum(this.status);
//    }

    /**
     * Entity -> FormDto
     */
    public static AdminItemForm of(Item item) {
        return AdminItemForm.builder()
                .id(item.getId())
                .name(item.getName())
                .englishName(item.getEnglishName())
                .basicDescription(item.getBasicDescription())
                .manufacturer(item.getManufacturer())
                .origin(item.getOrigin())
                .brand(item.getBrand())
                .barcode(item.getBarcode())
                .modelName(item.getModelName())
                .keyword(item.getKeyword())
                .beforeApprovalStatus(item.isBeforeApprovalStatus())
                .saleStatus(item.isSaleStatus())
                .productExposureStatus(item.isProductExposureStatus())
                .soldOutStatus(item.isSoldOutStatus())
                .noSaleStatus(item.isNoSaleStatus())
                .regularPrice(item.getRegularPrice())
                .salePrice(item.getSalePrice())
                .vatType(item.getVatType()
                        .name()
                        .toLowerCase())
                .fees(item.getFees())
                .point(item.getPoint())
                .stock(item.getStock())
                .stockNotificationQuantity(item.getStockNotificationQuantity())
                .minimumPurchaseQuantity(item.getMinimumPurchaseQuantity())
                .maximumPurchaseQuantity(item.getMaximumPurchaseQuantity())
                .productionTimeType(item.getProductionTimeType()
                        .name()
                        .toLowerCase())
                .productionYear(item.getProductionYear())
                .manufacturingYear(item.getManufacturingYear())
                .manufacturingMonth(item.getManufacturingMonth())
                .seasonYear(item.getSeasonYear())
                .season(item.getSeason()
                        .name()
                        .toLowerCase())
                .color(item.getColor())
                .detailDescription(item.getDetailDescription())
                .topDescription(item.getTopDescription())
                .bottomDescription(item.getBottomDescription())
                .type(item.getType())
                .productName(item.getProductName())
                .foodType(item.getFoodType())
                .producerAndLocation(item.getProducerAndLocation())
                .qualityMaintenancePeriod(item.getQualityMaintenancePeriod())
                .quantityPerUnit(item.getQuantityPerUnit())
                .rawMaterials(item.getRawMaterials())
                .nutrient(item.getNutrient())
                .geneticallyModifiedFood(item.getGeneticallyModifiedFood())
                .safetyPrecautions(item.getSafetyPrecautions())
                .importedFoodStationery(item.getImportedFoodStationery())
                .managerAndPhoneNumber(item.getManagerAndPhoneNumber())
                .bestBadge(item.isBestBadge())
                .newBadge(item.isNewBadge())
                .categoryId(item.getCategory()
                        .getId())
                .build();
    }

    public static AdminItemForm empty() {
        return AdminItemForm.builder()
                .id(0L)
                .build();
    }

    public VatType getVatType() {
        return VatType.getStringToEnum(this.vatType);
    }

    public ProductProductionTimeType getProductionTimeType() {
        return ProductProductionTimeType.getStringToEnum(this.productionTimeType);
    }

    public Season getSeason() {
        return Season.getStringToEnum(this.season);
    }

    /* 엔티티 빌더 */
    public ItemBuilder entityBuilder() {
        return new DefaultBuilder(this);
    }

    public ItemBuilder entityBuilder(Item item) {
        return new DefaultBuilder(item, this);
    }

    public interface ItemBuilder {
        ItemBuilder optionBuilders(List<ItemOptionBuilder> optionBuilders);

        ItemBuilder options(List<ItemOption> options);

        ItemBuilder images(List<ItemImage> images);

        ItemBuilder badges(List<Badge> badges);

        ItemBuilder category(Category category);

        Item build();

    }

    private static class DefaultBuilder implements ItemBuilder {
        private final Item item;
        private final AdminItemForm dto;

        public DefaultBuilder(AdminItemForm dto) {
            this.item = new Item();
            this.dto = dto;
        }

        public DefaultBuilder(Item item, AdminItemForm dto) {
            this.item = item;
            this.dto = dto;
        }

        @Override
        public ItemBuilder optionBuilders(List<ItemOptionBuilder> optionBuilders) {
            this.item.setOptionBuilders(optionBuilders);
            return this;
        }

        @Override
        public ItemBuilder options(List<ItemOption> options) {
            this.item.setOptions(options);
            return this;
        }

        @Override
        public ItemBuilder images(List<ItemImage> images) {
            this.item.setImages(images);
            return this;
        }

        @Override
        public ItemBuilder badges(List<Badge> badges) {
            this.item.setBadges(badges);
            return this;
        }

        @Override
        public ItemBuilder category(Category category) {
            this.item.setCategory(category);
            return this;
        }

        @Override
        public Item build() {
            this.item.setId(dto.getId());
            this.item.setName(dto.getName());
            this.item.setEnglishName(dto.getEnglishName());
            this.item.setBasicDescription(dto.getBasicDescription());
            this.item.setManufacturer(dto.getManufacturer());
            this.item.setOrigin(dto.getOrigin());
            this.item.setBrand(dto.getBrand());
            this.item.setBarcode(dto.getBarcode());
            this.item.setModelName(dto.getModelName());
            this.item.setKeyword(dto.getKeyword());

            this.item.setBeforeApprovalStatus(dto.isBeforeApprovalStatus());
            this.item.setSaleStatus(dto.isSaleStatus());
            this.item.setProductExposureStatus(dto.isProductExposureStatus());
            this.item.setSoldOutStatus(dto.isSoldOutStatus());
            this.item.setNoSaleStatus(dto.isNoSaleStatus());
            this.item.setRegularPrice(dto.getRegularPrice());
            this.item.setSalePrice(dto.getSalePrice());
            this.item.setVatType(dto.getVatType());
            this.item.setFees(dto.getFees());
            this.item.setPoint(dto.getPoint());
            this.item.setStock(dto.getStock());
            this.item.setStockNotificationQuantity(dto.getStockNotificationQuantity());
            this.item.setMinimumPurchaseQuantity(dto.getMinimumPurchaseQuantity());
            this.item.setMaximumPurchaseQuantity(dto.getMaximumPurchaseQuantity());

            this.item.setProductionTimeType(dto.getProductionTimeType());
            this.item.setProductionYear(dto.getProductionYear());
            this.item.setManufacturingYear(dto.getManufacturingYear());
            this.item.setManufacturingMonth(dto.getManufacturingMonth());
            this.item.setSeasonYear(dto.getSeasonYear());
            this.item.setSeason(dto.getSeason());
            this.item.setColor(dto.getColor());

            this.item.setDetailDescription(dto.getDetailDescription());
            this.item.setTopDescription(dto.getTopDescription());
            this.item.setBottomDescription(dto.getBottomDescription());

            this.item.setType(dto.getType());
            this.item.setProductName(dto.getProductName());
            this.item.setFoodType(dto.getFoodType());
            this.item.setProducerAndLocation(dto.getProducerAndLocation());
            this.item.setQualityMaintenancePeriod(dto.getQualityMaintenancePeriod());
            this.item.setQuantityPerUnit(dto.getQuantityPerUnit());
            this.item.setRawMaterials(dto.getRawMaterials());
            this.item.setNutrient(dto.getNutrient());
            this.item.setGeneticallyModifiedFood(dto.getGeneticallyModifiedFood());
            this.item.setSafetyPrecautions(dto.getSafetyPrecautions());
            this.item.setImportedFoodStationery(dto.getImportedFoodStationery());
            this.item.setManagerAndPhoneNumber(dto.getManagerAndPhoneNumber());

            this.item.setBestBadge(dto.isBestBadge());
            this.item.setNewBadge(dto.isNewBadge());
            return this.item;
        }
    }

}
