package com.shop.services.admin.notice.service;

import com.shop.commons.entity.BasePage;
import com.shop.commons.file.FileInfo;
import com.shop.commons.file.FileService;
import com.shop.models.badges.domain.Badge;
import com.shop.models.badges.service.BadgeService;
import com.shop.models.categories.domain.Category;
import com.shop.models.categories.service.CategoryService;
import com.shop.models.items.domain.Item;
import com.shop.models.items.domain.ItemImage;
import com.shop.models.items.domain.ItemOption;
import com.shop.models.items.domain.ItemOptionBuilder;
import com.shop.models.items.service.ItemImageService;
import com.shop.models.items.service.ItemOptionBuilderService;
import com.shop.models.items.service.ItemOptionService;
import com.shop.models.items.service.ItemService;
import com.shop.models.notice.domain.Notice;
import com.shop.models.notice.service.NoticeService;
import com.shop.services.admin.items.dto.AdminItemResponse;
import com.shop.services.admin.items.dto.form.AdminItemForm;
import com.shop.services.admin.items.dto.form.ItemOptionBuilderForm;
import com.shop.services.admin.items.dto.form.ItemOptionForm;
import com.shop.services.admin.items.dto.search.AdminItemSearchDto;
import com.shop.services.admin.items.dto.search.AdminItemSearchResponse;
import com.shop.services.admin.notice.dto.AdminNoticeResponse;
import com.shop.services.admin.notice.dto.form.AdminNoticeForm;
import com.shop.services.admin.notice.dto.search.AdminNoticeSearchDto;
import com.shop.services.admin.notice.dto.search.AdminNoticeSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

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
