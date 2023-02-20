package com.allddaom.services.service.addresses.controller;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.addresses.dto.ServiceAddressResponse;
import com.allddaom.services.service.addresses.service.ServiceAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
