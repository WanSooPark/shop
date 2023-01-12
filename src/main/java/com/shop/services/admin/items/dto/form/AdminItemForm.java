package com.shop.services.admin.items.dto.form;

import com.shop.models.badges.domain.Badge;
import com.shop.models.categories.domain.Category;
import com.shop.models.items.domain.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class AdminItemForm {
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
    private List<ItemOptionBuilder> optionBuilders; // 옵션 빌더
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
    private List<ItemImage> images;
    @Lob private String detailDescription; // 상품 상세 설명
    @Lob private String topDescription; // 상품 상단 내용
    @Lob private String bottomDescription; // 상품 하단 내용
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
    private List<Badge> badges; // 뱃지 (BEST, NEW)
    private Category category; // 카테고리
}
