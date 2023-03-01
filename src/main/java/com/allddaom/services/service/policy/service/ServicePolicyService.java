package com.allddaom.services.service.policy.service;

import com.allddaom.models.terms.domain.Terms;
import com.allddaom.models.terms.domain.TermsCategory;
import com.allddaom.models.terms.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServicePolicyService {

    private final TermsService termsService;

    public Terms find() {
        return termsService.findByCategory(TermsCategory.POLICY.toString());
    }
}
