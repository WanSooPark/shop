package com.allddaom.models.terms.infra.repo;

import com.allddaom.models.faq.domain.Faq;
import com.allddaom.models.terms.domain.Terms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TermsRepository extends JpaRepository<Terms, Long> {
   Terms findByCategory(String category);
}