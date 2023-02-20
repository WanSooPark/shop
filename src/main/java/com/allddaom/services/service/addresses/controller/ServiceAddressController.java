package com.allddaom.services.service.addresses.controller;

import com.allddaom.commons.errors.exceptions.BusinessException;
import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.addresses.dto.ServiceAddressResponse;
import com.allddaom.services.service.addresses.dto.form.ServiceAddressForm;
import com.allddaom.services.service.addresses.service.ServiceAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/address")
@RequiredArgsConstructor
public class ServiceAddressController {

    private final ServiceAddressService serviceAddressService;

    @GetMapping
    public String getAddressListView(@CurrentAccount Member member, Model model) {
        List<ServiceAddressResponse> addresses = serviceAddressService.findAddresses(member);
        model.addAttribute("addresses", addresses);
        return "mypage/address_list";
    }

    @GetMapping("/form")
    public String getAddressForm(@CurrentAccount Member member, @RequestParam(required = false) Long id, Model model) {
        boolean isNew = true;

        ServiceAddressForm address = ServiceAddressForm.empty();
        if (!ObjectUtils.isEmpty(id)) {
            try {
                address = serviceAddressService.getAddressForm(id, member);
                isNew = false;
            } catch (NoContentException noContentException) {
            }
        }
        model.addAttribute("address", address);

        model.addAttribute("isNew", isNew);
        return "mypage/address_form";
    }

    @PostMapping
    public String updateAddressForm(@CurrentAccount Member member, @Valid ServiceAddressForm dto, RedirectAttributes attributes) {
        try {
            ServiceAddressResponse response;
            if (dto.getId() == 0) {
                response = serviceAddressService.add(dto, member);
            } else {
                response = serviceAddressService.update(dto, member);
            }
        } catch (BusinessException businessException) {
            attributes.addAttribute("message", businessException.getMessage());
            attributes.addAttribute("exception", businessException);
            return "mypage/address_form";
        }
        return "redirect:/address";
    }

}
