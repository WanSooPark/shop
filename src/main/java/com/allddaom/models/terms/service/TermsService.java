package com.allddaom.models.terms.service;

import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.models.faq.domain.Faq;
import com.allddaom.models.faq.infra.repo.FaqRepository;
import com.allddaom.models.terms.domain.Terms;
import com.allddaom.models.terms.infra.repo.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TermsService {
    private final TermsRepository repository;

    public Terms findByCategory(String category) {
        return repository.findByCategory(category);
    }


    public Terms add(Terms item) {
        return repository.save(item);
    }

    public Terms update(Terms item) {
        return repository.save(item);
    }

}
