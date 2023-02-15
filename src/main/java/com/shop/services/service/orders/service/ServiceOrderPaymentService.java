package com.shop.services.service.orders.service;

import com.shop.commons.danal.dto.cardpayment.complete.CardPaymentCompleteResponse;
import com.shop.commons.danal.dto.cardpayment.ready.CardPaymentReadyRequest;
import com.shop.commons.danal.dto.cardpayment.ready.CardPaymentReadyResponse;
import com.shop.commons.danal.dto.virtualaccount.complete.VirtualAccountPaymentCompleteResponse;
import com.shop.commons.danal.dto.virtualaccount.ready.VirtualAccountPaymentReadyRequest;
import com.shop.commons.danal.dto.virtualaccount.ready.VirtualAccountPaymentReadyResponse;
import com.shop.commons.danal.service.cardpayment.DanalCardPaymentService;
import com.shop.commons.danal.service.virtualaccount.DanalVirtualAccountPaymentService;
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
    private final DanalVirtualAccountPaymentService danalVirtualAccountPaymentService;
    @Value("${infra.server_domain}")
    private String serverDomain;

    public ServiceOrderPaymentReadyDto.Response ready(Long id, PaymentType paymentType, String useragent, Member member) {
        Order order = orderService.findById(id);

        String amount = String.valueOf(order.getFinalAmount());
        String name = member.getName();
        String orderId = String.valueOf(order.getId());
        String itemName = order.getItemName();
        String userId = String.valueOf(member.getId());
        String userEmail = member.getEmail();
        String returnUrl = serverDomain + "/order/payment/" + paymentType.name() + "/complete";
        String cancelUrl = serverDomain + "/order/payment/" + paymentType.name() + "/cancel";
        String notiUrl = serverDomain + "/danal/payment/noti";

        ServiceOrderPaymentReadyDto.Response response = null;
        if (paymentType.equals(PaymentType.CARD_PAYMENT)) {
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
                    .byPassValue("orderId=" + orderId + ";paymentType=" + paymentType.name()) // "this=is;a=test;bypass=value"
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
        } else if (paymentType.equals(PaymentType.VIRTUAL_ACCOUNT)) {
            VirtualAccountPaymentReadyRequest cardPaymentReadyRequest = VirtualAccountPaymentReadyRequest.builder()
                    .amount(amount)
                    .name(name)
                    .accountHolder(member.getName())
                    .orderId(orderId)
                    .itemName(itemName)
                    .expireDatePlus(3L)
                    .useragent(useragent)
                    .userId(userId)
                    .userEmail(userEmail)
                    .returnUrl(returnUrl)
                    .cancelUrl(cancelUrl)
                    .notiUrl(notiUrl)
                    .byPassValue("orderId=" + orderId + ";paymentType=" + paymentType.name())
                    .build();

            VirtualAccountPaymentReadyResponse virtualAccountPaymentReadyResponse = danalVirtualAccountPaymentService.ready(cardPaymentReadyRequest);

            response = ServiceOrderPaymentReadyDto.Response.builder()
                    .success(virtualAccountPaymentReadyResponse.isSuccess())
                    .message(virtualAccountPaymentReadyResponse.getReturnMessage())
                    .returnCode(virtualAccountPaymentReadyResponse.getReturnCode())
                    .returnMessage(virtualAccountPaymentReadyResponse.getReturnMessage())
                    .startUrl(virtualAccountPaymentReadyResponse.getStartUrl())
                    .startParams(virtualAccountPaymentReadyResponse.getStartParams())
                    .tid(virtualAccountPaymentReadyResponse.getTid())
                    .orderId(virtualAccountPaymentReadyResponse.getOrderId())
                    .amount(virtualAccountPaymentReadyResponse.getAmount())
                    .build();
        }

        if (Objects.requireNonNull(response)
                .isSuccess()) {
            Payment payment = new Payment();
            payment.ready(response.getTid(), paymentType);
            payment.lastStatus(response.getReturnCode(), response.getReturnMessage());
            order.ready(payment);
        }

        return response;
    }

    public ServiceOrderPaymentCompleteDto.Response complete(String paymentTypeString, String returnParams) {
        PaymentType paymentType = PaymentType.getStringToEnum(paymentTypeString);

        ServiceOrderPaymentCompleteDto.Response response = null;
        if (paymentType.equals(PaymentType.CARD_PAYMENT)) {
            Map complete = danalCardPaymentService.decodeParams(returnParams);

            String orderId = (String) complete.get("ORDERID");
            Order order = orderService.findById(Long.valueOf(orderId));
            Payment payment = order.getPayment();
            if (!ObjectUtils.isEmpty(payment)) {
                payment.complete();
                payment.lastStatus((String) complete.get("RETURNCODE"), (String) complete.get("RETURNMSG"));
            }

            CardPaymentCompleteResponse cardPaymentResponse = danalCardPaymentService.complete(order, complete);

            if (!ObjectUtils.isEmpty(cardPaymentResponse)) {
                if (cardPaymentResponse.isSuccess()) {
                    payment.successCardPayment(
                            cardPaymentResponse.getTrxAmount(),
                            cardPaymentResponse.getTranDate(),
                            cardPaymentResponse.getTranTime(),
                            cardPaymentResponse.getCardCode(),
                            cardPaymentResponse.getCardName(),
                            cardPaymentResponse.getCardNo(),
                            cardPaymentResponse.getQuota(),
                            cardPaymentResponse.getCardAuthNo(),
                            cardPaymentResponse.getUsername()
                    );
                    payment.lastStatus(cardPaymentResponse.getReturnCode(), cardPaymentResponse.getReturnMessage());

                    List<OrderItem> items = order.getItems();
                    List<Long> cartItemIds = items.stream()
                            .map(OrderItem::getCartItemId)
                            .filter(cartItemId -> !ObjectUtils.isEmpty(cartItemId))
                            .collect(Collectors.toList());
                    cartItemService.deleteByIds(cartItemIds);
                }
            }

            response = ServiceOrderPaymentCompleteDto.Response.builder()
                    .success(response.isSuccess())
                    .message(response.getReturnMessage())
                    .paymentType(paymentTypeString)
                    .build();
        } else if (paymentType.equals(PaymentType.VIRTUAL_ACCOUNT)) {
            Map complete = danalVirtualAccountPaymentService.decodeParams(returnParams);

            String orderId = (String) complete.get("ORDERID");
            Order order = orderService.findById(Long.valueOf(orderId));
            Payment payment = order.getPayment();
            if (!ObjectUtils.isEmpty(payment)) {
                payment.complete();
                payment.lastStatus((String) complete.get("RETURNCODE"), (String) complete.get("RETURNMSG"));
            }

            VirtualAccountPaymentCompleteResponse virtualAccountPaymentResponse = danalVirtualAccountPaymentService.complete(order, complete);

            if (!ObjectUtils.isEmpty(virtualAccountPaymentResponse)) {
                if (virtualAccountPaymentResponse.isSuccess()) {
                    payment.issuanceOfVirtualAccount(
                            virtualAccountPaymentResponse.getBankCode(),
                            virtualAccountPaymentResponse.getBankName(),
                            virtualAccountPaymentResponse.getExpireDate(),
                            virtualAccountPaymentResponse.getExpireTime(),
                            virtualAccountPaymentResponse.getVirtualAccount(),
                            virtualAccountPaymentResponse.getIsCashReceipt(),
                            virtualAccountPaymentResponse.getAmount()
                    );
                    payment.lastStatus(virtualAccountPaymentResponse.getReturnCode(), virtualAccountPaymentResponse.getReturnMessage());

                    List<OrderItem> items = order.getItems();
                    List<Long> cartItemIds = items.stream()
                            .map(OrderItem::getCartItemId)
                            .filter(cartItemId -> !ObjectUtils.isEmpty(cartItemId))
                            .collect(Collectors.toList());
                    cartItemService.deleteByIds(cartItemIds);
                }
            } else {
                response = ServiceOrderPaymentCompleteDto.Response.builder()
                        .success(false)
                        .message("잘못된 paymentType 입니다.")
                        .paymentType("")
                        .build();
            }

            response = ServiceOrderPaymentCompleteDto.Response.builder()
                    .success(response.isSuccess())
                    .message(response.getReturnMessage())
                    .paymentType(paymentTypeString)
                    .bankCode(virtualAccountPaymentResponse.getBankCode())
                    .bankName(virtualAccountPaymentResponse.getBankName())
                    .expireDate(virtualAccountPaymentResponse.getExpireDate())
                    .expireTime(virtualAccountPaymentResponse.getExpireTime())
                    .virtualAccount(virtualAccountPaymentResponse.getVirtualAccount())
                    .isCashReceipt(virtualAccountPaymentResponse.getIsCashReceipt())
                    .virtualAccountAmount(virtualAccountPaymentResponse.getAmount())
                    .build();
        }

        return response;
    }
}
