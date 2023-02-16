package com.allddaom.services.service.orders.dto.payment;

import com.allddaom.models.payments.domain.Payment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServicePaymentResponse {
    private Long id;
    private Long amount;
    private String status;
    private String type;
    private String lastMessage;

    public static ServicePaymentResponse of(Payment payment) {
        return ServicePaymentResponse.builder()
                .id(payment.getId())
                .amount(Long.valueOf(payment.getAmount()))
                .status(payment.getStatus()
                        .name())
                .type(payment.getType()
                        .name())
                .lastMessage(payment.getLastMessage())
                .build();
    }
}
