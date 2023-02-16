package com.allddaom.services.admin.banners.controller;

import com.allddaom.commons.errors.exceptions.BusinessException;
import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.services.admin.banners.dto.AdminBannerResponse;
import com.allddaom.services.admin.banners.dto.form.AdminBannerForm;
import com.allddaom.services.admin.banners.dto.search.AdminBannerSearchDto;
import com.allddaom.services.admin.banners.service.AdminBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/banner")
@RequiredArgsConstructor
public class AdminBannerController {

    private final AdminBannerService adminBannerService;

    @GetMapping
    public String bannerListView(@Valid AdminBannerSearchDto.Request dto, @PageableDefault Pageable pageable, Model model) {
        AdminBannerSearchDto.Response response = adminBannerService.search(dto, pageable);
        model.addAttribute("bannerPage", response.getBannerPage());
        return "admin/banner/banner_list";
    }

    /**
     * 배너 Form View
     */
    @GetMapping("/form")
    public String bannerForm(@RequestParam(required = false) Long id, Model model) {
        boolean isNew = true;

        AdminBannerForm banner = AdminBannerForm.empty();
        if (!ObjectUtils.isEmpty(id)) {
            try {
                banner = adminBannerService.getBannerForm(id);
                isNew = false;
            } catch (NoContentException noContentException) {
            }
        }
        model.addAttribute("banner", banner);

        model.addAttribute("isNew", isNew);
        return "admin/banner/banner_form";
    }

    /**
     * 배너 등록/수정
     */
    @PostMapping
    public String newBanner(@Valid AdminBannerForm dto, RedirectAttributes attributes) {
        try {
            AdminBannerResponse response;
            if (dto.getId() == 0) {
                response = adminBannerService.add(dto);
            } else {
                response = adminBannerService.update(dto);
            }
        } catch (BusinessException businessException) {
            attributes.addAttribute("message", businessException.getMessage());
            attributes.addAttribute("exception", businessException);
            return "admin/banner/banner_form";
        }
        return "redirect:/admin/banner";
    }

}
