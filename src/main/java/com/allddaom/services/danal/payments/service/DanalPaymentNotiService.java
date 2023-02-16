package com.allddaom.services.danal.payments.service;

import com.allddaom.commons.danal.dto.virtualaccount.noti.VirtualAccountPaymentNotiResponse;
import com.allddaom.commons.danal.service.virtualaccount.DanalVirtualAccountPaymentService;
import com.allddaom.models.orders.domain.Order;
import com.allddaom.models.orders.service.OrderService;
import com.allddaom.models.payments.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class DanalPaymentNotiService {

    private final OrderService orderService;
    private final DanalVirtualAccountPaymentService danalVirtualAccountPaymentService;

    public void notiVirtualAccount(Map reqMap) {
        VirtualAccountPaymentNotiResponse response = danalVirtualAccountPaymentService.noti(reqMap);

        String orderId = response.getOrderId();
        Order order = orderService.findById(Long.valueOf(orderId));
        Payment payment = order.getPayment();
        if (response.isSuccess()) {
            payment.successVirtualAccount(
                    response.getTid(),
                    response.getAmount(),
                    response.getTranDate(),
                    response.getTranTime(),
                    response.getVirtualAccount(),
                    response.getAccountHolder(),
                    response.getDepositUsername(),
                    response.getUserId(),
                    response.getUserMail(),
                    response.getItemName(),
                    response.getByPassValue(),
                    response.getExpireDate(),
                    response.getExpireTime(),
                    response.getBankCode(),
                    response.getBankName(),
                    response.getIsCashReceipt()
            );
        }
        payment.lastStatus(response.getReturnCode(), response.getReturnMsg());
    }
}
