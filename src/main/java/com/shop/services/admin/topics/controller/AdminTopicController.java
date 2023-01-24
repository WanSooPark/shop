package com.shop.services.admin.topics.controller;

import com.shop.commons.errors.exceptions.BusinessException;
import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.services.admin.topics.dto.AdminTopicResponse;
import com.shop.services.admin.topics.dto.form.AdminTopicForm;
import com.shop.services.admin.topics.dto.form.AdminTopicFormDto;
import com.shop.services.admin.topics.dto.search.AdminTopicSearchDto;
import com.shop.services.admin.topics.service.AdminTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/topic")
@RequiredArgsConstructor
public class AdminTopicController {

    private final AdminTopicService adminTopicService;

    @GetMapping
    public String topicListView(@Valid AdminTopicSearchDto.Request dto, @PageableDefault Pageable pageable, Model model) {
        AdminTopicSearchDto.Response response = adminTopicService.search(dto, pageable);
        model.addAttribute("topicPage", response.getTopicPage());
        return "admin/topic/topic_list";
    }

    @GetMapping("/form")
    public String topicFormView(@Valid AdminTopicFormDto.Request dto, Model model) {
        boolean isNew = true;

        AdminTopicForm form = AdminTopicForm.empty();
        if (!ObjectUtils.isEmpty(dto.getCode())) {
            try {
                form = adminTopicService.getTopicForm(dto.getCode());
                isNew = false;
            } catch (NoContentException noContentException) {
            }
        }
        model.addAttribute("topic", form);
        model.addAttribute("isNew", isNew);
        return "admin/topic/topic_form";
    }

    /**
     * 기획전 등록/수정
     */
    @PostMapping
    public String newTopic(@Valid AdminTopicForm dto, RedirectAttributes attributes) {
        try {
            AdminTopicResponse response;
            response = adminTopicService.addOrUpdate(dto);
        } catch (BusinessException businessException) {
            attributes.addAttribute("message", businessException.getMessage());
            attributes.addAttribute("exception", businessException);
            return "admin/topic/topic_form";
        }
        return "redirect:/admin/topic";
    }

}
