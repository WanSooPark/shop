package com.allddaom.models.notice.infra.repo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.allddaom.models.notice.domain.Notice} entity
 */
@Data
public class NoticeDto implements Serializable {
   private final LocalDateTime createdDateTime;
   private final Long id;
   @Size(max = 255)
   @NotNull
   private final String title;
   @NotNull
   private final String content;
}