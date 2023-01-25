package com.shop.services.service.topics.controller;

import com.shop.commons.errors.exceptions.BusinessException;
import com.shop.commons.errors.exceptions.MvcException;
import com.shop.services.service.categories.dto.search.ServiceCategoryItemSearchDto;
import com.shop.services.service.topics.dto.ServiceTopicResponse;
import com.shop.services.service.topics.dto.ServiceTopicSideMenuResponse;
import com.shop.services.service.topics.dto.search.ServiceTopicItemSearchDto;
import com.shop.services.service.topics.service.ServiceTopicItemService;
import com.shop.services.service.topics.service.ServiceTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/topic/{topicCode}/item")
@RequiredArgsConstructor
public class TopicItemController {

    private final ServiceTopicItemService serviceTopicItemService;
    private final ServiceTopicService serviceTopicService;

    @GetMapping
    public String topicItemListView(@PathVariable String topicCode, @Valid ServiceTopicItemSearchDto.Request dto, @PageableDefault Pageable pageable, Model model, RedirectAttributes redirectAttributes) {
        try {
            ServiceCategoryItemSearchDto.Response response = serviceTopicItemService.search(topicCode, dto, pageable);
            ServiceTopicResponse serviceCategoryResponse = serviceTopicService.getTopic(topicCode);
            ServiceTopicSideMenuResponse serviceCategorySideMenuResponse = serviceTopicService.getSideMenu(topicCode);

            model.addAttribute("itemPage", response.getItemPage());
            model.addAttribute("topic", serviceCategoryResponse);
            model.addAttribute("topicSideMenu", serviceCategorySideMenuResponse);

            Long topicItemId = dto.getTopicItemId();
            if (ObjectUtils.isEmpty(topicItemId)) {
                topicItemId = 0L;
            }
            model.addAttribute("topicItemId", topicItemId);
        } catch (BusinessException businessException) {
            throw new MvcException(businessException);
        }
        return "item/topic/item_list";
    }

}
