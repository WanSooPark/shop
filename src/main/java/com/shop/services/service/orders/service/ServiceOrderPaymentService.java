package com.shop.services.service.orders.service;

import com.shop.commons.danal.dto.CardPaymentReadyRequest;
import com.shop.commons.danal.dto.CardPaymentReadyResponse;
import com.shop.commons.danal.service.DanalCardPaymentService;
import com.shop.models.members.domain.Member;
import com.shop.models.orders.domain.Order;
import com.shop.models.orders.service.OrderService;
import com.shop.models.payments.domain.PaymentType;
import com.shop.services.service.orders.dto.payment.ServiceOrderPaymentReadyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceOrderPaymentService {

    @Value("${infra.server_domain}")
    private String serverDomain;
    private final OrderService orderService;
    private final DanalCardPaymentService danalCardPaymentService;

    public ServiceOrderPaymentReadyDto.Response ready(Long id, ServiceOrderPaymentReadyDto.Request dto, Member member) {
        Order order = orderService.findById(id);
        PaymentType paymentType = dto.getPaymentType();
        // TODO 결제 방식별 분기

        ServiceOrderPaymentReadyDto.Response response = null;
        if (paymentType.equals(PaymentType.CARD_PAYMENT)) {
            String amount = String.valueOf(order.getFinalAmount());
            String orderId = String.valueOf(order.getId());
            String itemName = order.getItemName();
            String useragent = dto.getUseragent();
            String username = member.getUsername();
            String userId = String.valueOf(member.getId());
            String userEmail = member.getEmail();
            String returnUrl = serverDomain + "/api/orders/" + orderId + "/payment/complete";
            String cancelUrl = serverDomain + "/api/orders/" + orderId + "/payment/cancel";

            CardPaymentReadyRequest cardPaymentReadyRequest = CardPaymentReadyRequest.builder()
                    .amount(amount)
                    .orderId(orderId)
                    .itemName(itemName)
                    .useragent(useragent)
                    .username(username)
                    .userId(userId)
                    .userEmail(userEmail)
                    .returnUrl(returnUrl)
                    .cancelUrl(cancelUrl)
                    .build();

            CardPaymentReadyResponse cardPaymentReadyResponse = danalCardPaymentService.ready(cardPaymentReadyRequest);

            response = ServiceOrderPaymentReadyDto.Response.builder()
                    .success(true)
                    .message("카드결제 준비 완료")
                    .returnCode(cardPaymentReadyResponse.getReturnCode())
                    .returnMessage(cardPaymentReadyResponse.getReturnMessage())
                    .startUrl(cardPaymentReadyResponse.getStartUrl())
                    .startParams(cardPaymentReadyResponse.getStartParams())
                    .tid(cardPaymentReadyResponse.getTid())
                    .orderId(cardPaymentReadyResponse.getOrderId())
                    .amount(cardPaymentReadyResponse.getAmount())
                    .build();
        }

        return response;
    }

}
