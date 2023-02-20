package com.allddaom.models.addresses.service.event;

import com.allddaom.commons.errors.exceptions.InternalServerError;
import com.allddaom.models.addresses.domain.Address;
import com.allddaom.models.addresses.service.AddressService;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressEventListener {

    private final AddressService addressService;
    private final MemberService memberService;

    @Async
    @EventListener
    public void changeDefaultAddressEvent(ChangeDefaultAddressEvent event) {
        try {
            Address address = event.getAddress();
            Member member = event.getMember();

            member.updateAddress(address);
            member = memberService.update(member);
        } catch (Exception e) {
            throw new InternalServerError("", e);
        }
    }
}
