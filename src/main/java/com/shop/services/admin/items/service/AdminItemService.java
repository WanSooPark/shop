package com.shop.services.admin.items.service;

import com.shop.commons.entity.BasePage;
import com.shop.commons.file.FileInfo;
import com.shop.commons.file.FileService;
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
import com.shop.services.admin.items.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * 상품 추가
     */
    public AdminItemResponse addItem(AdminItemAddDto dto) {
        List<ItemOptionBuilder> optionBuilders = addItemOptionBuilders(dto.getOptionBuilders());
        List<ItemOption> options = addItemOptions(dto.getOptions());
        List<ItemImage> images = addItemImages(dto.getImageFiles());
        List<Badge> badges = getBadges(dto.getBadges());
        Category category = getCategory(dto.getCategoryId());

        Item item = dto.entityBuilder()
                .optionBuilders(optionBuilders)
                .options(options)
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
    public AdminItemResponse updateItem(Long id, AdminItemUpdateDto dto) {
        Item item = itemService.findById(id);

        List<ItemOptionBuilder> optionBuilders = item.getOptionBuilders();
        itemOptionBuilderService.deleteAll(optionBuilders); // 기존거 지우고
        optionBuilders = addItemOptionBuilders(dto.getOptionBuilders()); // 새로 저장

        List<ItemOption> options = item.getOptions();
        itemOptionService.deleteAll(options); // 기존거 지우고
        options = addItemOptions(dto.getOptions()); // 새로 저장

        List<ItemImage> images = addItemImages(dto.getImageFiles());
        List<Badge> badges = getBadges(dto.getBadges());
        Category category = getCategory(dto.getCategoryId());

        item = dto.entityUpdate(item)
                .optionBuilders(optionBuilders)
                .options(options)
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
    private List<ItemOptionBuilder> addItemOptionBuilders(List<ItemOptionBuilderAddDto> optionBuildersAddDto) {
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
    private List<ItemOption> addItemOptions(List<ItemOptionAddDto> optionsAddDto) {
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
        return imageFiles.stream()
                .map(imageFile -> {
                    FileInfo fileInfo = fileService.uploadFile(imageFile);

                    ItemImage itemImage = new ItemImage();
                    itemImage.setName(fileInfo.getName()); // 원본 이미지 명
                    itemImage.setOriginName(fileInfo.getOriginName()); // 저장한 이미지 명
                    itemImage.setUrl(fileInfo.getUrl()); // 이미지 주소
                    itemImage = itemImageService.add(itemImage);
                    return itemImage;
                })
                .collect(Collectors.toList());
    }

    /**
     * 뱃지 조회
     */
    private List<Badge> getBadges(List<String> badgesString) {
        return badgeService.findByCodeIn(badgesString);
    }

    /**
     * 분류(카테고리) 조회
     */
    private Category getCategory(Long categoryId) {
        return categoryService.findById(categoryId);
    }

    /**
     * 상품 목록 검색
     */
    public AdminItemSearchDto.Response search(AdminItemSearchDto.Request searchDto, Pageable pageable) {
        Page<Item> itemPage = itemService.searchForAdmin(searchDto.getSearchType(), searchDto.getSearch(), searchDto.getCategoryId(), pageable);
        Page<AdminItemResponse> adminItemPage = itemPage.map(AdminItemResponse::of);
        return AdminItemSearchDto.Response.builder()
                .items(new BasePage<>(adminItemPage))
                .build();
    }

    public AdminItemResponse getItem(Long id) {
        Item item = itemService.findById(id);
        return AdminItemResponse.of(item);
    }
}
