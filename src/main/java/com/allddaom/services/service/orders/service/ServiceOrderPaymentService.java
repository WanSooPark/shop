package com.allddaom.services.service.orders.service;

import com.allddaom.commons.danal.dto.cardpayment.complete.CardPaymentCompleteResponse;
import com.allddaom.commons.danal.dto.cardpayment.ready.CardPaymentReadyRequest;
import com.allddaom.commons.danal.dto.cardpayment.ready.CardPaymentReadyResponse;
import com.allddaom.commons.danal.dto.virtualaccount.complete.VirtualAccountPaymentCompleteResponse;
import com.allddaom.commons.danal.dto.virtualaccount.ready.VirtualAccountPaymentReadyRequest;
import com.allddaom.commons.danal.dto.virtualaccount.ready.VirtualAccountPaymentReadyResponse;
import com.allddaom.commons.danal.dto.wiretransfer.complete.WireTransferPaymentCompleteResponse;
import com.allddaom.commons.danal.dto.wiretransfer.ready.WireTransferPaymentReadyRequest;
import com.allddaom.commons.danal.dto.wiretransfer.ready.WireTransferPaymentReadyResponse;
import com.allddaom.commons.danal.service.cardpayment.DanalCardPaymentService;
import com.allddaom.commons.danal.service.virtualaccount.DanalVirtualAccountPaymentService;
import com.allddaom.commons.danal.service.wiretransfer.DanalWireTransferPaymentService;
import com.allddaom.models.carts.service.CartItemService;
import com.allddaom.models.members.domain.Member;
import com.allddaom.models.orders.domain.Order;
import com.allddaom.models.orders.domain.OrderItem;
import com.allddaom.models.orders.service.OrderService;
import com.allddaom.models.payments.domain.Payment;
import com.allddaom.models.payments.domain.PaymentType;
import com.allddaom.models.payments.service.PaymentService;
import com.allddaom.services.service.orders.dto.payment.ServiceOrderPaymentCancelDto;
import com.allddaom.services.service.orders.dto.payment.ServiceOrderPaymentCompleteDto;
import com.allddaom.services.service.orders.dto.payment.ServiceOrderPaymentReadyDto;
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
    private final DanalWireTransferPaymentService danalWireTransferPaymentService;
    @Value("${infra.server_domain}")
    private String serverDomain;

    public ServiceOrderPaymentReadyDto.Response ready(Long id, PaymentType paymentType, boolean isMobile, Member member) {
        Order order = orderService.findById(id);

        String amount = String.valueOf(order.getFinalAmount());
        String name = ObjectUtils.isEmpty(member) ? order.getOrdererName() : member.getName();
        String orderId = String.valueOf(order.getId());
        String itemName = order.getItemName();
        String userId = ObjectUtils.isEmpty(member) ? "" : String.valueOf(member.getId());
        String userEmail = ObjectUtils.isEmpty(member) ? order.getOrdererEmail() : member.getEmail();
        String returnUrl = serverDomain + "/order/payment/" + paymentType.name() + "/complete";
        String cancelUrl = serverDomain + "/order/payment/" + paymentType.name() + "/cancel?orderId=" + orderId;

        ServiceOrderPaymentReadyDto.Response response = null;
        if (paymentType.equals(PaymentType.CARD_PAYMENT)) {
            String useragent = isMobile ? "WM" : "WP"; // (WP: PC Web, WM: Mobile Web)
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
            String notiUrl = serverDomain + "/danal/payment/virtual-account/noti?orderId=" + orderId;
            String useragent = isMobile ? "MW" : "PC"; // (PC: PC Web, MW: Mobile Web)
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
        } else if (paymentType.equals(PaymentType.WIRE_TRANSFER)) {
            String useragent = isMobile ? "MW" : "PC"; // (PC: PC Web, MW: Mobile Web)
            WireTransferPaymentReadyRequest wireTransferPaymentReadyRequest = WireTransferPaymentReadyRequest.builder()
                    .amount(amount)
                    .name(name)
                    .orderId(orderId)
                    .itemName(itemName)
                    .useragent(useragent)
                    .userId(userId)
                    .userEmail(userEmail)
                    .returnUrl(returnUrl)
                    .cancelUrl(cancelUrl)
                    .byPassValue("orderId=" + orderId + ";paymentType=" + paymentType.name())
                    .build();

            WireTransferPaymentReadyResponse wireTransferPaymentReadyResponse = danalWireTransferPaymentService.ready(wireTransferPaymentReadyRequest);

            response = ServiceOrderPaymentReadyDto.Response.builder()
                    .success(wireTransferPaymentReadyResponse.isSuccess())
                    .message(wireTransferPaymentReadyResponse.getReturnMessage())
                    .returnCode(wireTransferPaymentReadyResponse.getReturnCode())
                    .returnMessage(wireTransferPaymentReadyResponse.getReturnMessage())
                    .startUrl(wireTransferPaymentReadyResponse.getStartUrl())
                    .startParams(wireTransferPaymentReadyResponse.getStartParams())
                    .tid(wireTransferPaymentReadyResponse.getTid())
                    .orderId(wireTransferPaymentReadyResponse.getOrderId())
                    .amount(wireTransferPaymentReadyResponse.getAmount())
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

        Order order = null;

        ServiceOrderPaymentCompleteDto.Response response = null;
        if (paymentType.equals(PaymentType.CARD_PAYMENT)) { // 신용카드
            Map complete = danalCardPaymentService.decodeParams(returnParams);

            String orderId = (String) complete.get("ORDERID");
            order = orderService.findById(Long.valueOf(orderId));
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
                }
                payment.lastStatus(cardPaymentResponse.getReturnCode(), cardPaymentResponse.getReturnMessage());
            }

            response = ServiceOrderPaymentCompleteDto.Response.builder()
                    .success(cardPaymentResponse.isSuccess())
                    .message(cardPaymentResponse.getReturnMessage())
                    .paymentType(paymentTypeString)
                    .build();
        } else if (paymentType.equals(PaymentType.VIRTUAL_ACCOUNT)) { // 가상계좌
            Map complete = danalVirtualAccountPaymentService.decodeParams(returnParams);

            String orderId = (String) complete.get("ORDERID");
            order = orderService.findById(Long.valueOf(orderId));
            Payment payment = order.getPayment();
            if (!ObjectUtils.isEmpty(payment)) {
                payment.complete();
                payment.lastStatus((String) complete.get("RETURNCODE"), (String) complete.get("RETURNMSG"));
            }

            VirtualAccountPaymentCompleteResponse virtualAccountPaymentResponse = danalVirtualAccountPaymentService.complete(order, complete);

            if (!ObjectUtils.isEmpty(virtualAccountPaymentResponse)) {
                if (virtualAccountPaymentResponse.isSuccess()) {
                    payment.issuanceOfVirtualAccount(
                            virtualAccountPaymentResponse.getTid(),
                            virtualAccountPaymentResponse.getAmount(),
                            virtualAccountPaymentResponse.getVirtualAccount(),
                            virtualAccountPaymentResponse.getAccountHolder(),
                            virtualAccountPaymentResponse.getUsername(),
                            virtualAccountPaymentResponse.getUserId(),
                            virtualAccountPaymentResponse.getUserMail(),
                            virtualAccountPaymentResponse.getItemName(),
                            virtualAccountPaymentResponse.getByPassValue(),
                            virtualAccountPaymentResponse.getExpireDate(),
                            virtualAccountPaymentResponse.getExpireTime(),
                            virtualAccountPaymentResponse.getBankCode(),
                            virtualAccountPaymentResponse.getBankName(),
                            virtualAccountPaymentResponse.getIsCashReceipt()
                    );
                }
                payment.lastStatus(virtualAccountPaymentResponse.getReturnCode(), virtualAccountPaymentResponse.getReturnMessage());
            }

            response = ServiceOrderPaymentCompleteDto.Response.builder()
                    .success(virtualAccountPaymentResponse.isSuccess())
                    .message(virtualAccountPaymentResponse.getReturnMessage())
                    .paymentType(paymentTypeString)
                    .tid(virtualAccountPaymentResponse.getTid())
                    .amount(virtualAccountPaymentResponse.getAmount())
                    .virtualAccount(virtualAccountPaymentResponse.getVirtualAccount())
                    .accountHolder(virtualAccountPaymentResponse.getAccountHolder())
                    .username(virtualAccountPaymentResponse.getUsername())
                    .userId(virtualAccountPaymentResponse.getUserId())
                    .userMail(virtualAccountPaymentResponse.getUserMail())
                    .itemName(virtualAccountPaymentResponse.getItemName())
                    .byPassValue(virtualAccountPaymentResponse.getByPassValue())
                    .expireDate(virtualAccountPaymentResponse.getExpireDate())
                    .expireTime(virtualAccountPaymentResponse.getExpireTime())
                    .bankCode(virtualAccountPaymentResponse.getBankCode())
                    .bankName(virtualAccountPaymentResponse.getBankName())
                    .isCashReceipt(virtualAccountPaymentResponse.getIsCashReceipt())
                    .build();
        } else if (paymentType.equals(PaymentType.WIRE_TRANSFER)) { // 계좌 이체
            Map complete = danalWireTransferPaymentService.decodeParams(returnParams);

            String orderId = (String) complete.get("ORDERID");
            order = orderService.findById(Long.valueOf(orderId));
            Payment payment = order.getPayment();
            if (!ObjectUtils.isEmpty(payment)) {
                payment.complete();
                payment.lastStatus((String) complete.get("RETURNCODE"), (String) complete.get("RETURNMSG"));
            }

            WireTransferPaymentCompleteResponse wireTransferPaymentCompleteResponse = danalWireTransferPaymentService.complete(order, complete);

            if (!ObjectUtils.isEmpty(wireTransferPaymentCompleteResponse)) {
                if (wireTransferPaymentCompleteResponse.isSuccess()) {
                    payment.successWireTransfer(
                            wireTransferPaymentCompleteResponse.getTid(),
                            wireTransferPaymentCompleteResponse.getItemName(),
                            wireTransferPaymentCompleteResponse.getAmount(),
                            wireTransferPaymentCompleteResponse.getAccountNo(),
                            wireTransferPaymentCompleteResponse.getBankCode(),
                            wireTransferPaymentCompleteResponse.getTransTime(),
                            wireTransferPaymentCompleteResponse.getUsername(),
                            wireTransferPaymentCompleteResponse.getUserId(),
                            wireTransferPaymentCompleteResponse.getUserPhone(),
                            wireTransferPaymentCompleteResponse.getUserMail(),
                            wireTransferPaymentCompleteResponse.getByPassValue()
                    );
                }
                payment.lastStatus(wireTransferPaymentCompleteResponse.getReturnCode(), wireTransferPaymentCompleteResponse.getReturnMessage());
            }

            response = ServiceOrderPaymentCompleteDto.Response.builder()
                    .success(wireTransferPaymentCompleteResponse.isSuccess())
                    .message(wireTransferPaymentCompleteResponse.getReturnMessage())
                    .paymentType(paymentTypeString)
                    .accountNo(wireTransferPaymentCompleteResponse.getAccountNo())
                    .bankCode(wireTransferPaymentCompleteResponse.getBankCode())
                    .transTime(wireTransferPaymentCompleteResponse.getTransTime())
                    .username(wireTransferPaymentCompleteResponse.getUsername())
                    .userId(wireTransferPaymentCompleteResponse.getUserId())
                    .userPhone(wireTransferPaymentCompleteResponse.getUserPhone())
                    .userEmail(wireTransferPaymentCompleteResponse.getUserMail())
                    .build();
        } else {
            response = ServiceOrderPaymentCompleteDto.Response.builder()
                    .success(false)
                    .message("잘못된 paymentType 입니다.")
                    .paymentType("")
                    .build();
        }

        if (response.isSuccess() && !ObjectUtils.isEmpty(order)) {
            List<OrderItem> items = order.getItems();
            List<Long> cartItemIds = items.stream()
                    .map(OrderItem::getCartItemId)
                    .filter(cartItemId -> !ObjectUtils.isEmpty(cartItemId))
                    .collect(Collectors.toList());
            cartItemService.deleteByIds(cartItemIds);
        }

        return response;
    }

    public ServiceOrderPaymentCancelDto.Response cancel(Long orderId, String paymentType) {
        Order order = orderService.findById(orderId);
        order.cancel();
        return ServiceOrderPaymentCancelDto.Response.builder()
                .success(true)
                .message("주문 결제 취소")
                .paymentType(paymentType)
                .build();
    }
}
