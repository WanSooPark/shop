package com.allddaom.services.service.faq.service;

import com.allddaom.models.faqs.domain.Faq;
import com.allddaom.models.faqs.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceFaqService {

    private final FaqService faqService;

    public List<Faq> findAll() {
        return faqService.findAll();
    }
}
