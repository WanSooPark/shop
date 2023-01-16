package com.shop.services.service.sign.dto;

import com.shop.models.addresses.domain.Address;
import lombok.Data;

@Data
public class SignUpForm {
    private String username; // email
    private String password;
    private String nickname;
    private String name;
    private String email;
    private Address address;
    private String tel;

    public String getUsername() {
        return this.email;
    }
}
