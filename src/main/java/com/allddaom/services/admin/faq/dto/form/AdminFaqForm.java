package com.allddaom.services.admin.faq.dto.form;

import com.allddaom.models.faq.domain.Faq;
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
public class AdminFaqForm {
   @Builder.Default
   private Long id = 0L;
   private String title;
   private String content;
   private String category;
   private Boolean isNew;
   private LocalDateTime createdDateTime;

   public static AdminFaqForm of(Faq faq) {
      return AdminFaqForm.builder()
              .id(faq.getId())
              .title(faq.getTitle())
              .content(faq.getContent())
              .category(faq.getCategory())
              .createdDateTime(faq.getCreatedDateTime())
              .build();
   }

   public Faq entityBuilder() {
      return Faq.builder()
              .id(this.getId())
              .title(this.getTitle())
              .content(this.getContent())
              .category(this.getCategory())
              .build();
   }
}
