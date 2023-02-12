package com.shop.services.service.notice.service;

import com.shop.commons.entity.BasePage;
import com.shop.models.notice.domain.Notice;
import com.shop.models.notice.service.NoticeService;
import com.shop.services.service.notice.dto.form.ServiceNoticeForm;
import com.shop.services.service.notice.dto.search.ServiceNoticeSearchDto;
import com.shop.services.service.notice.dto.search.ServiceNoticeSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceNoticeService {

    private final NoticeService noticeService;

    public ServiceNoticeSearchDto.Response search(ServiceNoticeSearchDto.Request searchDto, Pageable pageable) {
        Page<Notice> noticePage = null;
        if (StringUtils.hasText(searchDto.getSearch())) {
            noticePage = noticeService.search(searchDto.getSearch(), pageable);
        } else {
            noticePage = noticeService.findAll(pageable);
        }

        Page<ServiceNoticeSearchResponse> adminNoticePage = noticePage.map(ServiceNoticeSearchResponse::of);

        return ServiceNoticeSearchDto.Response.builder()
                .noticePage(new BasePage<>(adminNoticePage))
                .build();
    }

    public ServiceNoticeForm getNoticeForm(Long id) {
        Notice notice = noticeService.findById(id);
        noticeService.updateViewCountById(id);
        return ServiceNoticeForm.of(notice);
    }
}
