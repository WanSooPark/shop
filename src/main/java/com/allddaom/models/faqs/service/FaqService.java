package com.allddaom.models.faqs.service;

import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.models.faqs.domain.Faq;
import com.allddaom.models.faqs.infra.repo.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FaqService {
    private final FaqRepository repository;

    public Page<Faq> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Faq> findAll() {
        return repository.findByOrderByIdDesc();
    }

    public Page<Faq> search(String search, Pageable pageable) {
        return repository.findByTitleContainsOrContentContains(search, search, pageable);
    }

    public Page<Faq> searchCategory(String category, Pageable pageable) {
        return repository.findByCategoryOrderByCreatedDateTimeDesc(category, pageable);
    }

    public Faq add(Faq item) {
        return repository.save(item);
    }

    public Faq update(Faq item) {
        return repository.save(item);
    }

    public void delete(List<Long> ids) {
        repository.deleteByIdIn(ids);
    }

    public Faq findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("유효하지 않은 Faq id 입니다."));
    }
}
