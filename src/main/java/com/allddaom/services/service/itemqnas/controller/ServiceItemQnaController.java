package com.allddaom.services.service.itemqnas.controller;

import com.allddaom.services.service.itemqnas.dto.search.ServiceItemReviewSearchDto;
import com.allddaom.services.service.itemqnas.service.ServiceItemQnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item/{itemId}/qna")
@RequiredArgsConstructor
public class ServiceItemQnaController {

    private final ServiceItemQnaService serviceItemReviewService;

    @GetMapping
    private String itemReviewListView(@PathVariable Long itemId, ServiceItemReviewSearchDto.Request dto, Model model) {
        return "item/qna/item_qna_list";
    }

}
