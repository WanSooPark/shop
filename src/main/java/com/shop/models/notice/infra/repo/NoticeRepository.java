package com.shop.models.notice.infra.repo;

import com.shop.models.categories.domain.Category;
import com.shop.models.items.domain.Item;
import com.shop.models.items.infra.repo.QDSLItemRepository;
import com.shop.models.notice.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

   Page<Notice> findAll(Pageable pageable);

   Page<Notice> findByTitleContainsOrContentContains(String title, String content, Pageable pageable);

   void deleteByIdIn(Collection<Long> ids);

   @Transactional
   @Modifying
   @Query("update Notice n set n.viewCount = n.viewCount+1 where n.id = ?1")
   int updateViewCountById(Long id);
}
