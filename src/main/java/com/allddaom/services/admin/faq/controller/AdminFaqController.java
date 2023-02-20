package com.allddaom.services.admin.faq.controller;

import com.allddaom.commons.errors.exceptions.BusinessException;
import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.services.admin.faq.dto.form.AdminFaqForm;
import com.allddaom.services.admin.faq.dto.search.AdminFaqSearchDto;
import com.allddaom.services.admin.faq.service.AdminFaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin/faq")
@RequiredArgsConstructor
public class AdminFaqController {

    private final AdminFaqService adminFaqService;


    @GetMapping("/faq_list")
    public String faq_list(@Valid AdminFaqSearchDto.Request searchDto, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        AdminFaqSearchDto.Response response = adminFaqService.search(searchDto, pageable);
        model.addAttribute("itemPage", response.getFaqPage());
        return "admin/faq/faq_list";
    }

    @GetMapping("/form")
    public String itemListView(@RequestParam(required = false) Long id, Model model) {
        boolean isNew = true;

        AdminFaqForm form = new AdminFaqForm();
        if (!ObjectUtils.isEmpty(id)) {
            try {
                form = adminFaqService.getFaqForm(id);
                isNew = false;
            } catch (NoContentException noContentException) {
            }
        }

        model.addAttribute("item", form);
        model.addAttribute("isNew", isNew);
        return "admin/faq/faq_form";
    }


    @PostMapping("/form")
    public @ResponseBody HashMap<String, Object> newNotice(@Valid AdminFaqForm dto, Authentication authentication) {
        HashMap<String, Object> retMap = new HashMap<>();

        try {
            if (dto.getIsNew()) {
                adminFaqService.addFaq(dto);
            } else {
                adminFaqService.updateNotice(dto);
            }

            retMap.put("status", "success");
        } catch (BusinessException businessException) {
            retMap.put("status", "fail");
        }

        return retMap;
    }

    @PostMapping("/delete")
    public @ResponseBody HashMap<String, Object> deleteNotice(@RequestParam(value = "list[]") List<Long> list) {
        HashMap<String, Object> retMap = new HashMap<>();

        try {
            adminFaqService.delete(list);

            retMap.put("status", "success");
        } catch (BusinessException businessException) {
            retMap.put("status", "fail");
        }

        return retMap;
    }

}
