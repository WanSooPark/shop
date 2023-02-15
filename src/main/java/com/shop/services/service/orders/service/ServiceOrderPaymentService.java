package com.shop.services.service.orders.service;

import com.shop.commons.danal.dto.complete.ready.CardPaymentCompleteResponse;
import com.shop.commons.danal.dto.ready.CardPaymentReadyRequest;
import com.shop.commons.danal.dto.ready.CardPaymentReadyResponse;
import com.shop.commons.danal.service.DanalCardPaymentService;
import com.shop.models.carts.service.CartItemService;
import com.shop.models.members.domain.Member;
import com.shop.models.orders.domain.Order;
import com.shop.models.orders.domain.OrderItem;
import com.shop.models.orders.service.OrderService;
import com.shop.models.payments.domain.Payment;
import com.shop.models.payments.domain.PaymentType;
import com.shop.models.payments.service.PaymentService;
import com.shop.services.service.orders.dto.payment.ServiceOrderPaymentCompleteDto;
import com.shop.services.service.orders.dto.payment.ServiceOrderPaymentReadyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceOrderPaymentService {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final CartItemService cartItemService;
    private final DanalCardPaymentService danalCardPaymentService;
    @Value("${infra.server_domain}")
    private String serverDomain;

    public ServiceOrderPaymentReadyDto.Response ready(Long id, PaymentType paymentType, String useragent, Member member) {
        Order order = orderService.findById(id);
//        PaymentType paymentType = dto.getPaymentType();
        // TODO 결제 방식별 분기

        ServiceOrderPaymentReadyDto.Response response = null;
        if (paymentType.equals(PaymentType.CARD_PAYMENT)) {
            String amount = String.valueOf(order.getFinalAmount());
            String orderId = String.valueOf(order.getId());
            String itemName = order.getItemName();
//            String useragent = dto.getUseragent();
            String name = member.getName();
            String userId = String.valueOf(member.getId());
            String userEmail = member.getEmail();
            String returnUrl = serverDomain + "/order/payment/complete";
            String cancelUrl = serverDomain + "/order/payment/cancel";

            CardPaymentReadyRequest cardPaymentReadyRequest = CardPaymentReadyRequest.builder()
                    .amount(amount)
                    .orderId(orderId)
                    .itemName(itemName)
                    .useragent(useragent)
                    .name(name)
                    .userId(userId)
                    .userEmail(userEmail)
                    .returnUrl(returnUrl)
                    .cancelUrl(cancelUrl)
                    .byPassValue("orderId=" + orderId) // "this=is;a=test;bypass=value"
                    .build();

            CardPaymentReadyResponse cardPaymentReadyResponse = danalCardPaymentService.ready(cardPaymentReadyRequest);

            response = ServiceOrderPaymentReadyDto.Response.builder()
                    .success(cardPaymentReadyResponse.isSuccess())
                    .message(cardPaymentReadyResponse.getReturnMessage())
                    .returnCode(cardPaymentReadyResponse.getReturnCode())
                    .returnMessage(cardPaymentReadyResponse.getReturnMessage())
                    .startUrl(cardPaymentReadyResponse.getStartUrl())
                    .startParams(cardPaymentReadyResponse.getStartParams())
                    .tid(cardPaymentReadyResponse.getTid())
                    .orderId(cardPaymentReadyResponse.getOrderId())
                    .amount(cardPaymentReadyResponse.getAmount())
                    .build();
        }

        if (Objects.requireNonNull(response)
                .isSuccess()) {
            Payment payment = new Payment();
            payment.ready(response.getTid(), PaymentType.CARD_PAYMENT);
            payment.lastStatus(response.getReturnCode(), response.getReturnMessage());
            order.ready(payment);
        }

        return response;
    }

    public ServiceOrderPaymentCompleteDto.Response complete(String returnParams) {
        Map complete = danalCardPaymentService.decodeParams(returnParams);

        String orderId = (String) complete.get("ORDERID");
        Order order = orderService.findById(Long.valueOf(orderId));
        Payment payment = order.getPayment();
        if (!ObjectUtils.isEmpty(payment)) {
            payment.complete();
            payment.lastStatus((String) complete.get("RETURNCODE"), (String) complete.get("RETURNMSG"));
        }

        CardPaymentCompleteResponse response = danalCardPaymentService.complete(order, complete);

        if (!ObjectUtils.isEmpty(response)) {
            if (response.isSuccess()) {
                payment.success(
                        response.getTrxAmount(),
                        response.getTranDate(),
                        response.getTranTime(),
                        response.getCardCode(),
                        response.getCardName(),
                        response.getCardNo(),
                        response.getQuota(),
                        response.getCardAuthNo(),
                        response.getUsername()
                );
                payment.lastStatus(response.getReturnCode(), response.getReturnMessage());

                List<OrderItem> items = order.getItems();
                List<Long> cartItemIds = items.stream()
                        .map(OrderItem::getCartItemId)
                        .filter(cartItemId -> !ObjectUtils.isEmpty(cartItemId))
                        .collect(Collectors.toList());
                cartItemService.deleteByIds(cartItemIds);
            }
        }

        return ServiceOrderPaymentCompleteDto.Response.builder()
                .success(response.isSuccess())
                .message(response.getReturnMessage())
                .build();
    }
}
