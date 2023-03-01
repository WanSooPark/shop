package com.allddaom.services.service.terms.service;

import com.allddaom.models.faq.domain.Faq;
import com.allddaom.models.faq.service.FaqService;
import com.allddaom.models.terms.domain.Terms;
import com.allddaom.models.terms.domain.TermsCategory;
import com.allddaom.models.terms.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceTermsService {

    private final TermsService termsService;

    public Terms find() {
        return termsService.findByCategory(TermsCategory.TERMS.toString());
    }
}
