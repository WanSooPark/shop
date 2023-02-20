package com.allddaom.services.service.orders.dto;

import com.allddaom.models.members.domain.Member;
import com.allddaom.models.orders.domain.Order;
import com.allddaom.models.orders.domain.OrderAddress;
import com.allddaom.models.orders.domain.OrderStatus;
import com.allddaom.services.service.orders.dto.address.ServiceOrderAddressDto;
import com.allddaom.services.service.orders.dto.item.ServiceOrderItemDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ServiceOrderDto {

    @Data
    public static class Request {
        private String ordererName;
        private String ordererGeneralPhoneNumber;
        private String ordererCellPhoneNumber;
        private boolean ordererAvailableReceiveSms;
        private String ordererEmail;

        private boolean changeShippingAddressToMemberInformation; // 현재 배송지 정보를 회원정보로 저장 여부

        @NotBlank
        private String recipientName;
        private String recipientGeneralPhoneNumber;
        @NotBlank
        private String recipientCellPhoneNumber;
        private String deliveryMemo;
        private String orderMemo;
        @Valid
        @NotNull
        private ServiceOrderAddressDto.Request recipientAddress;

        @Valid
        @NotNull
        private List<ServiceOrderItemDto.Request> items;

        public Order toEntity(Member member, OrderAddress address) {
            Order order = new Order();
            order.setMember(member);
            order.setAddress(address);
            order.setStatus(OrderStatus.PENDING);
            order.setOrdererName(this.ordererName);
            order.setOrdererGeneralPhoneNumber(this.ordererGeneralPhoneNumber);
            order.setOrdererCellPhoneNumber(this.ordererCellPhoneNumber);
            order.setOrdererAvailableReceiveSms(this.ordererAvailableReceiveSms);
            order.setOrdererEmail(this.ordererEmail);
            order.setChangeShippingAddressToMemberInformation(this.changeShippingAddressToMemberInformation);
            order.setRecipientName(this.recipientName);
            order.setRecipientGeneralPhoneNumber(this.recipientGeneralPhoneNumber);
            order.setRecipientCellPhoneNumber(this.recipientCellPhoneNumber);
            order.setDeliveryMemo(this.deliveryMemo);
            order.setOrderMemo(this.orderMemo);
            return order;
        }
    }

    @Data
    @Builder
    public static class Response {
        private String message;
        private boolean success;
        private ServiceOrderResponse order;
    }

}
