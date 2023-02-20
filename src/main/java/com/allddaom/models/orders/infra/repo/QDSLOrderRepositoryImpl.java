package com.allddaom.models.orders.infra.repo;

import com.allddaom.commons.querydsl.CustomQuerydslRepositorySupport;
import com.allddaom.models.members.domain.Member;
import com.allddaom.models.orders.domain.Order;
import com.allddaom.models.orders.domain.OrderStatus;
import com.allddaom.models.orders.domain.QOrder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Repository
public class QDSLOrderRepositoryImpl extends CustomQuerydslRepositorySupport implements QDSLOrderRepository {

    public QDSLOrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public Page<Order> search(String statusGroup, LocalDateTime startDateTime, LocalDateTime endDateTime, Member member, Pageable pageable) {
        QOrder order = QOrder.order;

        List<OrderStatus> statusList = new LinkedList<>();
        if (!ObjectUtils.isEmpty(statusGroup) && statusGroup.equals("invalid")) {
            statusList.add(OrderStatus.CANCEL);
            statusList.add(OrderStatus.EXCHANGE);
            statusList.add(OrderStatus.RETURN);
        } else {
            statusList.add(OrderStatus.BEFORE_DEPOSIT);
            statusList.add(OrderStatus.PREPARING_FOR_DELIVERY);
            statusList.add(OrderStatus.IN_DELIVERY);
            statusList.add(OrderStatus.DELIVERY_COMPLETE);
        }

        JPQLQuery<Order> query = from(order);
        query
                .where(
                        this.in(order.status, statusList.toArray())
                        , this.between(order.createdDateTime, startDateTime, endDateTime)
                        , this.equals(order.member, member)
                )
                .orderBy(order.createdDateTime.desc());

        JPQLQuery<Order> jpqlQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Order> fetchResults = jpqlQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
