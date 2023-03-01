package com.allddaom.services.admin.terms.controller;

import com.allddaom.commons.errors.exceptions.BusinessException;
import com.allddaom.models.terms.domain.TermsCategory;
import com.allddaom.services.admin.terms.dto.form.AdminTermsForm;
import com.allddaom.services.admin.terms.service.AdminTermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminTermsController {

    private final AdminTermsService adminTermsService;

    @GetMapping("/terms/form")
    public String form(Model model) {
        AdminTermsForm form = adminTermsService.getTermsForm(TermsCategory.TERMS.toString());
        if (form==null) {
            return "admin/terms/terms_form_new";
        } else {
            model.addAttribute("item", form);
            return "admin/terms/terms_form";
        }
    }

    @PostMapping("/terms/form")
    public @ResponseBody HashMap<String, Object> postForm(@Valid AdminTermsForm dto, Authentication authentication) {
        HashMap<String, Object> retMap = new HashMap<>();

        try {
            if (dto.getIsNew()) {
                adminTermsService.addTerms(dto);
            } else {
                adminTermsService.updateTerms(dto);
            }

            retMap.put("status", "success");
        } catch (BusinessException businessException) {
            retMap.put("status", "fail");
        }

        return retMap;
    }

    @GetMapping("/policy/form")
    public String policyForm(Model model) {
        AdminTermsForm form = adminTermsService.getTermsForm(TermsCategory.POLICY.toString());
        if (form==null) {
            return "admin/policy/policy_form_new";
        } else {
            model.addAttribute("item", form);
            return "admin/policy/policy_form";
        }
    }

    @PostMapping("/policy/form")
    public @ResponseBody HashMap<String, Object> policyForm(@Valid AdminTermsForm dto, Authentication authentication) {
        HashMap<String, Object> retMap = new HashMap<>();

        try {
            if (dto.getIsNew()) {
                adminTermsService.addTerms(dto);
            } else {
                adminTermsService.updateTerms(dto);
            }

            retMap.put("status", "success");
        } catch (BusinessException businessException) {
            retMap.put("status", "fail");
        }

        return retMap;
    }
}
