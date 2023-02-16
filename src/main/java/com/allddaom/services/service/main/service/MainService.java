package com.allddaom.services.service.main.service;

import com.allddaom.models.banners.domain.Banner;
import com.allddaom.models.banners.domain.BannerStatus;
import com.allddaom.models.banners.service.BannerService;
import com.allddaom.models.categories.domain.Category;
import com.allddaom.models.categories.service.CategoryService;
import com.allddaom.models.items.domain.Item;
import com.allddaom.models.items.service.ItemService;
import com.allddaom.models.members.domain.Member;
import com.allddaom.models.topics.domain.Topic;
import com.allddaom.models.topics.domain.TopicItem;
import com.allddaom.models.topics.domain.TopicItemStatus;
import com.allddaom.models.topics.service.TopicItemService;
import com.allddaom.models.topics.service.TopicService;
import com.allddaom.services.service.main.dto.banner.MainBannerResponse;
import com.allddaom.services.service.main.dto.cateogry.MainCategoryResponse;
import com.allddaom.services.service.main.dto.topic.MainTopicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MainService {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final TopicService topicService;
    private final TopicItemService topicItemService;
    private final BannerService bannerService;

    public MainTopicResponse findTopicItemsByTopicCode(String topicCode, Member member) {
        Topic topic = topicService.findByCode(topicCode); // 하드코딩

        List<TopicItem> topicItems = topicItemService.findEffectiveByTopicCodeAndStatusOrderByOrd(topic, TopicItemStatus.ACTIVATE);
        topic.setTopicItems(topicItems);

        // TODO 찜 추가
        return MainTopicResponse.of(topic);
    }

    public MainTopicResponse findShowMainTopicItems(Member member) {
        List<Topic> showMainTopics = topicService.findByShowMain(true);
        if (ObjectUtils.isEmpty(showMainTopics)) {
            return null;
        }

        Topic topic = showMainTopics.get(0);

        List<TopicItem> topicItems = topicItemService.findEffectiveByTopicCodeAndStatusOrderByOrd(topic, TopicItemStatus.ACTIVATE);
        topic.setTopicItems(topicItems);

        // TODO 찜 추가
        return MainTopicResponse.of(topic);
    }

    public List<MainCategoryResponse> recItemsCategories(Member member) {
        List<Item> items = itemService.findByRecBadge(true);

        List<Category> categories = new LinkedList<>();
        items.forEach(item -> {
            if (!ObjectUtils.isEmpty(item.getCategory())) {
                if (categories.stream()
                        .noneMatch(category -> category.getId()
                                .equals(item.getCategory()
                                        .getId()))) {
                    Category category = item.getCategory();
                    category.getItems()
                            .add(item);
                    categories.add(category);
                } else {
                    categories.forEach(category -> {
                        if (category.getId()
                                .equals(item.getCategory()
                                        .getId())) {
                            category.getItems()
                                    .add(item);
                        }
                    });
                }
            }
        });

        return categories.stream()
                .map(MainCategoryResponse::of)
                .collect(Collectors.toList());
    }

    public MainCategoryResponse hotItemItemsCategory(Member member) {
//        Category category = categoryService.findById(4L);
        Category category = categoryService.findByName("HOT ITEM");

        List<Item> items = itemService.findByCategory(category);
        category.setItems(items);
        return MainCategoryResponse.of(category);
    }

    public List<MainBannerResponse> findBanners() {
        List<Banner> banners = bannerService.findByStatus(BannerStatus.USE);
        return banners.stream()
                .map(MainBannerResponse::of)
                .collect(Collectors.toList());
    }
}
