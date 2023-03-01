package com.allddaom.services.admin.terms.service;

import com.allddaom.models.terms.domain.Terms;
import com.allddaom.models.terms.service.TermsService;
import com.allddaom.services.admin.terms.dto.form.AdminTermsForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminTermsService {

    private final TermsService termsService;

    public AdminTermsForm getTermsForm(String category) {
        Terms byCategory = termsService.findByCategory(category);
        return byCategory == null ? null : AdminTermsForm.of(byCategory);
    }

    public void addTerms(AdminTermsForm dto) {
        Terms terms = dto.entityBuilder();
        termsService.add(terms);
    }

    public void updateTerms(AdminTermsForm dto) {
        Terms terms = dto.entityBuilder();
        termsService.update(terms);
    }
}
