package com.shop.models.notice.domain;

import com.shop.commons.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Size(max = 255)
   @NotNull
   @Column(name = "title", nullable = false)
   private String title;

   @NotNull
   @Lob
   @Column(name = "content", nullable = false)
   private String content;

   @Column(name = "view_count", nullable = false)
   @ColumnDefault("0")
   private Long viewCount;

   @CreatedBy
   @Column(updatable = false)
   private String createdBy;

   @LastModifiedBy
   private String modifiedBy;

   @CreatedDate
   @Column(updatable = false)
   private LocalDateTime createdDateTime;

   @LastModifiedDate
   private LocalDateTime updatedDateTime;
}