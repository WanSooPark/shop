package com.shop.services.service.notice.dto.form;

import com.shop.models.notice.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceNoticeForm {
   @Builder.Default
   private Long id = 0L;
   private String title;
   private String content;
   private Boolean isNew;
   private Long viewCount;
   private LocalDateTime createdDateTime;

   public static ServiceNoticeForm of(Notice item) {
      return ServiceNoticeForm.builder()
              .id(item.getId())
              .title(item.getTitle())
              .content(StringUtils.replace(item.getContent(), "\n", "<br/>"))
              .viewCount(item.getViewCount())
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
