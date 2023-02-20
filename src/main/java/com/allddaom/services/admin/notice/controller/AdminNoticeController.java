package com.allddaom.services.admin.notice.controller;

import com.allddaom.commons.errors.exceptions.BusinessException;
import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.services.admin.notice.dto.form.AdminNoticeForm;
import com.allddaom.services.admin.notice.dto.search.AdminNoticeSearchDto;
import com.allddaom.services.admin.notice.service.AdminNoticeService;
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
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final AdminNoticeService adminNoticeService;

    @GetMapping
    public String noticeList(@Valid AdminNoticeSearchDto.Request searchDto, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        AdminNoticeSearchDto.Response response = adminNoticeService.search(searchDto, pageable);
        model.addAttribute("itemPage", response.getNoticePage());
        return "admin/notice/notice_list";
    }

    @GetMapping("/form")
    public String itemListView(@RequestParam(required = false) Long id, Model model) {
        boolean isNew = true;

//        AdminItemForm form = AdminItemForm.empty();
        AdminNoticeForm form = new AdminNoticeForm();
        if (!ObjectUtils.isEmpty(id)) {
            try {
                form = adminNoticeService.getNoticeForm(id);
                isNew = false;
            } catch (NoContentException noContentException) {
            }
        }

        model.addAttribute("item", form);
        model.addAttribute("isNew", isNew);
        return "admin/notice/notice_form";
    }

    @PostMapping("/form")
    public @ResponseBody HashMap<String, Object> newNotice(@Valid AdminNoticeForm dto, Authentication authentication) {
        HashMap<String, Object> retMap = new HashMap<>();

        try {
            if (dto.getIsNew()) {
                adminNoticeService.addNotice(dto);
            } else {
                adminNoticeService.updateNotice(dto);
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
            adminNoticeService.delete(list);

            retMap.put("status", "success");
        } catch (BusinessException businessException) {
            retMap.put("status", "fail");
        }

        return retMap;
    }
}
