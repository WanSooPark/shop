package com.allddaom.services.service.itemreviews.controller;

import com.allddaom.services.service.itemreviews.dto.search.ServiceItemReviewSearchDto;
import com.allddaom.services.service.itemreviews.service.ServiceItemReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item/{itemId}/review")
@RequiredArgsConstructor
public class ServiceItemReviewController {

    private final ServiceItemReviewService serviceItemReviewService;

    @GetMapping
    private String itemReviewListView(@PathVariable Long itemId, ServiceItemReviewSearchDto.Request dto, Model model) {
        return "item/review/item_review_list";
    }

}
