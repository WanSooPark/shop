package com.shop.services.admin.notice.dto.form;

import com.shop.models.badges.domain.Badge;
import com.shop.models.categories.domain.Category;
import com.shop.models.items.domain.*;
import com.shop.models.notice.domain.Notice;
import com.shop.services.admin.items.dto.form.AdminItemForm;
import com.shop.services.admin.items.dto.form.ItemOptionBuilderForm;
import com.shop.services.admin.items.dto.form.ItemOptionForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminNoticeForm {
   @Builder.Default
   private Long id = 0L;
   private String title;
   private String content;
   private Boolean isNew;
   private LocalDateTime createdDateTime;

   public static AdminNoticeForm of(Notice item) {
      return AdminNoticeForm.builder()
              .id(item.getId())
              .title(item.getTitle())
              .content(item.getContent())
              .createdDateTime(item.getCreatedDateTime())
              .build();
   }

   public Notice entityBuilder() {
      return Notice.builder()
              .id(this.getId())
              .title(this.getTitle())
              .content(this.getContent())
              .build();
   }
}
