package com.allddaom.models.faq.infra.repo;

import com.allddaom.models.faq.domain.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    Page<Faq> findAll(Pageable pageable);

    @Query("select f from Faq f order by f.id DESC")
    List<Faq> findByOrderByIdDesc();

    Page<Faq> findByTitleContainsOrContentContains(String title, String content, Pageable pageable);

    Page<Faq> findByCategoryOrderByCreatedDateTimeDesc(String category, Pageable pageable);

    void deleteByIdIn(Collection<Long> ids);

}
