package com.allddaom.services.service.notice.controller;

import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.services.service.notice.dto.form.ServiceNoticeForm;
import com.allddaom.services.service.notice.dto.search.ServiceNoticeSearchDto;
import com.allddaom.services.service.notice.service.ServiceNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class ServiceNoticeController {

    private final ServiceNoticeService serviceNoticeService;

    @GetMapping("/notice_list")
    public String noticeList(@Valid ServiceNoticeSearchDto.Request searchDto, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        ServiceNoticeSearchDto.Response response = serviceNoticeService.search(searchDto, pageable);
        model.addAttribute("itemPage", response.getNoticePage());
        model.addAttribute("search", searchDto.getSearch());
        return "notice/notice_list";
    }

    @GetMapping("/notice_search")
    public String noticeSearch(@Valid ServiceNoticeSearchDto.Request searchDto, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        ServiceNoticeSearchDto.Response response = serviceNoticeService.search(searchDto, pageable);
        model.addAttribute("itemPage", response.getNoticePage());
        return "notice/notice_list";
    }

    @GetMapping("/notice_view")
    public String noticeView(@RequestParam(required = false) Long id, Model model) {
        ServiceNoticeForm form = new ServiceNoticeForm();
        try {
            form = serviceNoticeService.getNoticeForm(id);
            model.addAttribute("item", form);
        } catch (NoContentException noContentException) {
        }

        return "notice/notice_view";
    }

}
