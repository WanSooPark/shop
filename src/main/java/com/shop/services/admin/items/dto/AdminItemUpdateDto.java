package com.shop.services.admin.items.dto;

import com.shop.models.badges.domain.Badge;
import com.shop.models.categories.domain.Category;
import com.shop.models.items.domain.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import java.util.List;

@Data
public class AdminItemUpdateDto {

    /* 기본정보 */
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
    private String status; // 상품 상태 (승인 전, 판매중, 상품 노출, 품절, 판매금지)
    private Long regularPrice; // 정상가
    private Long salePrice; // 판매가
    private String vatType; // 상품과세 유형 (과세, 비과세)
    private Long fees; // 수수료
    private Long point; // 포인트
    private Long stock; // 재고
    private Long stockNotificationQuantity; // 통보 재고 수량
    private Long minimumPurchaseQuantity; // 최소 구매 수량
    private Long maximumPurchaseQuantity; // 최대 구매 가능 수량
    private List<ItemOptionBuilderAddDto> optionBuilders; // 옵션 빌더
    private List<ItemOptionAddDto> options; // 옵션

    /* 상품정보 */
    private String productionTimeType; // 상품 생산 시기 구분 (정상, 이월)
    private Integer productionYear; // 생산년도
    private Integer manufacturingYear; // 제조일 년도
    private Integer manufacturingMonth; // 제조일 월
    private String seasonYear; // 시즌년도
    private String season; // 시즌 (FW, SS)
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
    private String itemGroup; // 상품군
    private String ingredient; // 제품 소재
    private String color2; // 색상
    private String dimensions; // 치수
    private String manufacturer2; // 제조사(수입자/병행수입)
    private String manufacturingCountry; // 제조국
    private String washingMethodAndHandlingPrecautions; // 세탁방법 및 취급시 주의사항
    private String dateOfManufacture; // 제조연월
    private String qualityAssuranceStandard; // 품질보증기준
    private String asManagerAndPhoneNumber; // A/S 책임자와 전화번호
    private String subjectToKCSafetyCertification; // KC 안전인증 대상 유무
    private Boolean income; // 수입여부
    private String kinds; // 종류
    private String weight; // 상품무게
    private String dimensionsXYZ; // 상품치수 가로_세로_높이

    /* 분류 */
    private List<String> badges; // 뱃지 (BEST, NEW)
    private Long categoryId; // 카테고리 객체

    /* enum 바인딩 */
    public ItemSellStatus getStatus() {
        // TODO
        return ItemSellStatus.SALE;
    }

    public VatType getVatType() {
        // TODO
        return VatType.USE;
    }

    public ProductProductionTimeType getProductionTimeType() {
        // TODO
        return ProductProductionTimeType.NORMAL;
    }

    public Season getSeason() {
        // TODO
        return Season.FW;
    }

    /* 엔티티 빌더 */
    public ItemUpdater entityUpdate(Item item) {
        return new DefaultUpdater(item, this);
    }

    public interface ItemUpdater {
        ItemUpdater optionBuilders(List<ItemOptionBuilder> optionBuilders);

        ItemUpdater options(List<ItemOption> options);

        ItemUpdater images(List<ItemImage> images);

        ItemUpdater badges(List<Badge> badges);

        ItemUpdater category(Category category);

        Item build();

    }

    private static class DefaultUpdater implements ItemUpdater {
        private final Item item;
        private final AdminItemUpdateDto dto;

        public DefaultUpdater(Item item, AdminItemUpdateDto dto) {
            this.item = item;
            this.dto = dto;
        }

        @Override
        public ItemUpdater optionBuilders(List<ItemOptionBuilder> optionBuilders) {
            this.item.setOptionBuilders(optionBuilders);
            return this;
        }

        @Override
        public ItemUpdater options(List<ItemOption> options) {
            this.item.setOptions(options);
            return this;
        }

        @Override
        public ItemUpdater images(List<ItemImage> images) {
            this.item.setImages(images);
            return this;
        }

        @Override
        public ItemUpdater badges(List<Badge> badges) {
            this.item.setBadges(badges);
            return this;
        }

        @Override
        public ItemUpdater category(Category category) {
            this.item.setCategory(category);
            return this;
        }

        @Override
        public Item build() {
//            this.item.setId(dto.getId());
            this.item.setName(dto.getName());
            this.item.setEnglishName(dto.getEnglishName());
            this.item.setBasicDescription(dto.getBasicDescription());
            this.item.setManufacturer(dto.getManufacturer());
            this.item.setOrigin(dto.getOrigin());
            this.item.setBrand(dto.getBrand());
            this.item.setBarcode(dto.getBarcode());
            this.item.setModelName(dto.getModelName());
            this.item.setKeyword(dto.getKeyword());

            this.item.setStatus(dto.getStatus());
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
            this.item.setMaximumPurchaseQuantity(dto.getMaximumPurchaseQuantity());

            this.item.setDetailDescription(dto.getDetailDescription());
            this.item.setTopDescription(dto.getTopDescription());
            this.item.setBottomDescription(dto.getBottomDescription());

//            this.item.setItemGroup(dto.getItemGroup());
//            this.item.setIngredient(dto.getIngredient());
//            this.item.setColor2(dto.getColor2());
//            this.item.setDimensions(dto.getDimensions());
//            this.item.setManufacturer2(dto.getManufacturer2());
//            this.item.setManufacturingCountry(dto.getManufacturingCountry());
//            this.item.setWashingMethodAndHandlingPrecautions(dto.getWashingMethodAndHandlingPrecautions());
//            this.item.setDateOfManufacture(dto.getDateOfManufacture());
//            this.item.setQualityAssuranceStandard(dto.getQualityAssuranceStandard());
//            this.item.setAsManagerAndPhoneNumber(dto.getAsManagerAndPhoneNumber());
//            this.item.setSubjectToKCSafetyCertification(dto.getSubjectToKCSafetyCertification());
//            this.item.setIncome(dto.getIncome());
//            this.item.setKinds(dto.getKinds());
//            this.item.setWeight(dto.getWeight());
//            this.item.setDimensionsXYZ(dto.getDimensionsXYZ());
            return this.item;
        }
    }

}
