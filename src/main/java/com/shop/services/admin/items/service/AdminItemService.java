package com.shop.services.admin.items.service;

import com.shop.commons.entity.BasePage;
import com.shop.commons.file.FileInfo;
import com.shop.commons.file.FileService;
import com.shop.commons.file.FileUploader;
import com.shop.models.badges.domain.Badge;
import com.shop.models.badges.service.BadgeService;
import com.shop.models.categories.domain.Category;
import com.shop.models.categories.service.CategoryService;
import com.shop.models.items.domain.Item;
import com.shop.models.items.domain.ItemImage;
import com.shop.models.items.domain.ItemOption;
import com.shop.models.items.domain.ItemOptionBuilder;
import com.shop.models.items.service.ItemImageService;
import com.shop.models.items.service.ItemOptionBuilderService;
import com.shop.models.items.service.ItemOptionService;
import com.shop.models.items.service.ItemService;
import com.shop.services.admin.items.dto.AdminItemResponse;
import com.shop.services.admin.items.dto.form.AdminItemForm;
import com.shop.services.admin.items.dto.form.ItemOptionBuilderForm;
import com.shop.services.admin.items.dto.form.ItemOptionForm;
import com.shop.services.admin.items.dto.search.AdminItemSearchDto;
import com.shop.services.admin.items.dto.search.AdminItemSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminItemService {

    private final ItemService itemService;
    private final ItemImageService itemImageService;
    private final ItemOptionService itemOptionService;
    private final ItemOptionBuilderService itemOptionBuilderService;
    private final CategoryService categoryService;
    private final BadgeService badgeService;

    private final FileService fileService;
    private final FileUploader fileUploader;

    /**
     * 상품 추가
     */
    public AdminItemResponse addItem(AdminItemForm dto) {
        List<ItemOptionBuilder> optionBuilders = addItemOptionBuilders(dto.getOptionBuilders());
        List<ItemOption> options = addItemOptions(dto.getOptions());
        ItemImage mainImage = addItemImage(dto.getMainImageFile());
        List<ItemImage> images = addItemImages(dto.getImageFiles());
        List<Badge> badges = getBadges(dto.getBadges());
        Category category = getCategory(dto.getCategoryId());

        Item item = dto.entityBuilder()
                .optionBuilders(optionBuilders)
                .options(options)
                .mainImage(mainImage)
                .images(images)
                .badges(badges)
                .category(category)
                .build();

        item = itemService.add(item);
        return AdminItemResponse.of(item);
    }

    /**
     * 상품 정보 수정
     */
    public AdminItemResponse updateItem(AdminItemForm dto) {
        Long id = dto.getId();
        Item item = itemService.findById(id);

        List<ItemOptionBuilder> optionBuilders = item.getOptionBuilders();
        itemOptionBuilderService.deleteAll(optionBuilders); // 기존거 지우고
        optionBuilders = addItemOptionBuilders(dto.getOptionBuilders()); // 새로 저장

        List<ItemOption> options = item.getOptions();
        itemOptionService.deleteAll(options); // 기존거 지우고
        options = addItemOptions(dto.getOptions()); // 새로 저장

        ItemImage mainImage = item.getMainImage();
        if (!dto.getMainImageFile().isEmpty()) {
            // TODO 삭제해야함
            mainImage = addItemImage(dto.getMainImageFile());
        }

        List<ItemImage> images = item.getImages();
        if (!ObjectUtils.isEmpty(dto.getImageFiles())) {
            // TODO 삭제해야함
            images = addItemImages(dto.getImageFiles());
        }

        List<Badge> badges = getBadges(dto.getBadges());
        Category category = getCategory(dto.getCategoryId());

        item = dto.entityBuilder()
                .optionBuilders(optionBuilders)
                .options(options)
                .mainImage(mainImage)
                .images(images)
                .badges(badges)
                .category(category)
                .build();

        item = itemService.update(item); // 안해도 됨
        return AdminItemResponse.of(item);
    }

    /**
     * 상품 옵션 생성기 추가
     */
    private List<ItemOptionBuilder> addItemOptionBuilders(List<ItemOptionBuilderForm> optionBuildersAddDto) {
        if (ObjectUtils.isEmpty(optionBuildersAddDto)) {
            return null;
        }
        return optionBuildersAddDto.stream()
                .map(optionBuilderAddDto -> {
                    ItemOptionBuilder itemOptionBuilder = optionBuilderAddDto.entityBuilder()
                            .build();
                    return itemOptionBuilderService.add(itemOptionBuilder);
                })
                .collect(Collectors.toList());
    }

    /**
     * 상품 옵션 추가
     */
    private List<ItemOption> addItemOptions(List<ItemOptionForm> optionsAddDto) {
        if (ObjectUtils.isEmpty(optionsAddDto)) {
            return null;
        }
        return optionsAddDto.stream()
                .map(optionAddDto -> {
                    ItemOption itemOption = optionAddDto.entityBuilder()
                            .build();
                    return itemOptionService.add(itemOption);
                })
                .collect(Collectors.toList());
    }

    /**
     * 상품 이미지 추가(저장)
     */
    private List<ItemImage> addItemImages(List<MultipartFile> imageFiles) {
        if (ObjectUtils.isEmpty(imageFiles)) {
            return null;
        }
        return imageFiles.stream()
                .map(this::addItemImage)
                .collect(Collectors.toList());
    }

    private ItemImage addItemImage(MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return null;
        }
        String imageUrl = fileUploader.upload2(imageFile, "ITEMS");

        ItemImage itemImage = new ItemImage();
        itemImage.setName(imageFile.getName()); // 원본 이미지 명
        itemImage.setOriginName(imageFile.getOriginalFilename()); // 저장한 이미지 명
        itemImage.setUrl(imageUrl); // 이미지 주소
        itemImage = itemImageService.add(itemImage);
        return itemImage;
    }

    /**
     * 뱃지 조회
     */
    @Deprecated
    private List<Badge> getBadges(List<String> badgesString) {
        return badgeService.findByCodeIn(badgesString);
    }

    /**
     * 분류(카테고리) 조회
     */
    private Category getCategory(Long categoryId) {
        if (ObjectUtils.isEmpty(categoryId)) {
            return null;
        }
        return categoryService.findById(categoryId);
    }

    /**
     * 상품 목록 검색
     */
    public AdminItemSearchDto.Response search(AdminItemSearchDto.Request searchDto, Pageable pageable) {
        Page<Item> itemPage = itemService.searchForAdmin(searchDto.getSearchType(), searchDto.getSearch(), searchDto.getCategoryId(), pageable);
        Page<AdminItemSearchResponse> adminItemPage = itemPage.map(AdminItemSearchResponse::of);
        return AdminItemSearchDto.Response.builder()
                .itemPage(new BasePage<>(adminItemPage))
                .build();
    }

    /**
     * 상품 조회
     */
    public AdminItemResponse getItem(Long id) {
        Item item = itemService.findById(id);
        return AdminItemResponse.of(item);
    }

    /**
     * 상품 Form 조회
     */
    public AdminItemForm getItemForm(Long id) {
        Item item = itemService.findById(id);
        return AdminItemForm.of(item);
    }
}
