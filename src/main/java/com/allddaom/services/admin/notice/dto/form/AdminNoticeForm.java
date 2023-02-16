package com.allddaom.services.admin.notice.dto.form;

import com.allddaom.models.notice.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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