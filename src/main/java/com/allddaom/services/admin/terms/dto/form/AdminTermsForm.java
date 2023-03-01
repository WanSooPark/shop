package com.allddaom.services.admin.terms.dto.form;

import com.allddaom.models.faq.domain.Faq;
import com.allddaom.models.terms.domain.Terms;
import com.allddaom.models.terms.domain.TermsCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTermsForm {
   @Builder.Default
   private Long id = 0L;
   private String content;
   private String category;
   private Boolean isNew;
   private LocalDateTime createdDateTime;

   public static AdminTermsForm of(Terms term) {
      return AdminTermsForm.builder()
              .id(term.getId())
              .content(term.getContent())
              .category(term.getCategory())
              .createdDateTime(term.getCreatedDateTime())
              .build();
   }

   public Terms entityBuilder() {
      return Terms.builder()
              .id(this.getId())
              .content(this.getContent())
              .category(this.getCategory())
              .build();
   }
}
