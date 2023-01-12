package com.shop.services.admin.items.dto.form;

import com.shop.models.categories.domain.Category;
import com.shop.models.items.domain.ItemOption;
import com.shop.models.items.domain.ItemOptionBuilder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import java.util.List;

@Data
public class AdminItemForm {
    private String method; // post, put
    private Long id = 0L; // 상품 코드
    private String name = "name"; // 상품명
    private String englishName = "englishName"; // 영문 상품명
    private String basicDescription = "basicDescription"; // 상품 기본 설명
    private String manufacturer = "manufacturer"; // 제조사
    private String origin = "origin"; // 원산지
    private String brand = "brand"; // 브랜드
    private String barcode = "barcode"; // 바코드
    private String modelName = "modelName"; // 모델명
    private String keyword = "keyword"; // 검색어
    /* 판매정보 */
//    private String status; // 상품 상태 (승인 전, 판매중, 상품 노출, 품절, 판매금지)
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
    private List<ItemOptionBuilder> optionBuilders; // 옵션 빌더
    private List<ItemOption> options; // 옵션
    /* 상품정보 */
    private String productionTimeType = "CARRY_OVER"; // ProductProductionTimeType 상품 생산 시기 구분 NORMAL("정상"), CARRY_OVER("이월");
    private Integer productionYear = 2023; // 생산년도
    private Integer manufacturingYear = 2023; // 제조일 년도
    private Integer manufacturingMonth = 5; // 제조일 월
    private String seasonYear; // 시즌년도
    private String season = "FW"; // 시즌 (FW, SS) Season
    private String color; // 색상
    /* 이미지 */
    private List<MultipartFile> images;

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
    private Category category; // 카테고리
}
