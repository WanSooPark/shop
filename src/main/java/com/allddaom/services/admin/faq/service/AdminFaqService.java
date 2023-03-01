package com.allddaom.services.admin.faq.service;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.models.faq.domain.Faq;
import com.allddaom.models.faq.service.FaqService;
import com.allddaom.services.admin.faq.dto.form.AdminFaqForm;
import com.allddaom.services.admin.faq.dto.search.AdminFaqSearchDto;
import com.allddaom.services.admin.faq.dto.search.AdminFaqSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminFaqService {

    private final FaqService faqService;

    public AdminFaqSearchDto.Response search(AdminFaqSearchDto.Request searchDto, Pageable pageable) {
        Page<Faq> faqs = faqService.findAll(pageable);
        if (StringUtils.hasText(searchDto.getCategory())) {
            faqs = faqService.searchCategory(searchDto.getCategory(), pageable);
        } else {
            faqs = faqService.findAll(pageable);
        }

        Page<AdminFaqSearchResponse> adminFaqPages = faqs.map(AdminFaqSearchResponse::of);

        return AdminFaqSearchDto.Response.builder()
                .faqPage(new BasePage<>(adminFaqPages))
                .build();
    }

    public void addFaq(AdminFaqForm dto) {
        Faq item = dto.entityBuilder();
        faqService.add(item);
    }

    public void updateNotice(AdminFaqForm dto) {
        Faq item = dto.entityBuilder();
        faqService.update(item);
    }

    public AdminFaqForm getFaqForm(Long id) {
        Faq item = faqService.findById(id);
        return AdminFaqForm.of(item);
    }

    public void delete(List<Long> ids) {
        faqService.delete(ids);
    }

}
