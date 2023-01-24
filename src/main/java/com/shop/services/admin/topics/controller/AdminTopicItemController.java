package com.shop.services.admin.topics.controller;

import com.shop.commons.errors.exceptions.BusinessException;
import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.services.admin.items.dto.search.AdminItemSearchDto;
import com.shop.services.admin.items.service.AdminItemService;
import com.shop.services.admin.topics.dto.AdminTopicItemResponse;
import com.shop.services.admin.topics.dto.AdminTopicResponse;
import com.shop.services.admin.topics.dto.form.AdminTopicItemForm;
import com.shop.services.admin.topics.dto.form.AdminTopicItemFormDto;
import com.shop.services.admin.topics.dto.search.AdminTopicItemSearchDto;
import com.shop.services.admin.topics.service.AdminTopicItemService;
import com.shop.services.admin.topics.service.AdminTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/topic/{topicCode}")
@RequiredArgsConstructor
public class AdminTopicItemController {

    private final AdminTopicService adminTopicService;
    private final AdminTopicItemService adminTopicItemService;
    private final AdminItemService adminItemService;

    @GetMapping
    public String topicTopicView(@PathVariable String topicCode, @Valid AdminTopicItemSearchDto.Request dto, @PageableDefault Pageable pageable, Model model, RedirectAttributes redirectAttributes) {
        if (!validTopicCode(topicCode, redirectAttributes)) {
            return "redirect:/admin/topic";
        }

        AdminTopicItemSearchDto.Response response = adminTopicItemService.search(topicCode, dto, pageable);
        model.addAttribute("topicCode", topicCode);

        AdminTopicResponse adminTopicResponse = adminTopicService.getTopic(topicCode);
        model.addAttribute("topicName", adminTopicResponse.getName());
        model.addAttribute("topicItemPage", response.getTopicItemPage());
        return "admin/topic/topic_item_list";
    }

    private boolean validTopicCode(String topicCode, RedirectAttributes redirectAttributes) {
        if (ObjectUtils.isEmpty(topicCode)) {
            redirectAttributes.addFlashAttribute("message", "유효하지 않은 topic code 입니다.");
            return false;
        }
        if (!adminTopicService.existsByTopicCode(topicCode)) {
            redirectAttributes.addFlashAttribute("message", "유효하지 않은 topic code 입니다.");
            return false;
        }
        return true;
    }

    @GetMapping("/form")
    public String topicItemFormView(@PathVariable String topicCode, @Valid AdminTopicItemFormDto.Request dto, @PageableDefault(size = 5) Pageable pageable, Model model, RedirectAttributes redirectAttributes) {
        if (!validTopicCode(topicCode, redirectAttributes)) {
            return "redirect:/admin/topic/" + topicCode;
        }

        boolean isNew = true;

        AdminTopicItemForm form = AdminTopicItemForm.empty();
        if (!ObjectUtils.isEmpty(dto.getId())) {
            try {
                form = adminTopicItemService.getTopicItemForm(dto.getId());
                isNew = false;
            } catch (NoContentException noContentException) {
            }
        }

        model.addAttribute("topicCode", topicCode);

        AdminTopicResponse adminTopicResponse = adminTopicService.getTopic(topicCode);
        model.addAttribute("topicName", adminTopicResponse.getName());

        model.addAttribute("topicItem", form);
        model.addAttribute("isNew", isNew);

        AdminItemSearchDto.Request searchDto = AdminItemSearchDto.Request.builder()
                .search(dto.getSearch())
                .build();
        AdminItemSearchDto.Response response = adminItemService.search(searchDto, pageable);
        model.addAttribute("itemPage", response.getItemPage());

        return "admin/topic/topic_item_form";
    }

    @PostMapping
    public String newTopicItem(@PathVariable String topicCode, @Valid AdminTopicItemForm dto, RedirectAttributes attributes) {
        try {
            AdminTopicItemResponse response = adminTopicItemService.addOrUpdate(topicCode, dto);
        } catch (BusinessException businessException) {
            attributes.addAttribute("message", businessException.getMessage());
            attributes.addAttribute("exception", businessException);
            return "admin/topic/topic_item_form";
        }
        return "redirect:/admin/topic/" + topicCode;
    }

}
