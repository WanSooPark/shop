package com.allddaom.services.admin.notice.service;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.models.notices.domain.Notice;
import com.allddaom.models.notices.service.NoticeService;
import com.allddaom.services.admin.notice.dto.form.AdminNoticeForm;
import com.allddaom.services.admin.notice.dto.search.AdminNoticeSearchDto;
import com.allddaom.services.admin.notice.dto.search.AdminNoticeSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminNoticeService {

    private final NoticeService noticeService;

    public AdminNoticeSearchDto.Response search(AdminNoticeSearchDto.Request searchDto, Pageable pageable) {
        Page<Notice> noticePage = noticeService.findAll(pageable);
        Page<AdminNoticeSearchResponse> adminNoticePage = noticePage.map(AdminNoticeSearchResponse::of);

        return AdminNoticeSearchDto.Response.builder()
                .noticePage(new BasePage<>(adminNoticePage))
                .build();
    }

    public void addNotice(AdminNoticeForm dto) {
        Notice item = dto.entityBuilder();
        item.setViewCount(0L);

        noticeService.add(item);
//      return AdminNoticeResponse.of(item);
    }

    public void updateNotice(AdminNoticeForm dto) {
        Notice item = dto.entityBuilder();

        noticeService.update(item);
//      return AdminNoticeResponse.of(item);
    }

    public AdminNoticeForm getNoticeForm(Long id) {
        Notice notice = noticeService.findById(id);
        return AdminNoticeForm.of(notice);
    }

    public void delete(List<Long> ids) {
        noticeService.delete(ids);
    }
}
