package com.shop.services.service.topics.infra.repo;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.commons.querydsl.CustomQuerydslRepositorySupport;
import com.shop.models.items.domain.Item;
import com.shop.models.items.domain.QItem;
import com.shop.models.topics.domain.QTopicItem;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicItem;
import com.shop.models.topics.domain.TopicItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QDSLServiceTopicItemItemRepositoryImpl extends CustomQuerydslRepositorySupport implements QDSLServiceTopicItemItemRepository {

    @Autowired
    private JPAQueryFactory queryFactory;


    public QDSLServiceTopicItemItemRepositoryImpl() {
        super(Item.class);
    }


//    @Override
//    public Page<Item> searchItem(Topic topic, String search, Pageable pageable) {
//        QItem item = QItem.item;
//        QTopicItem topicItem = QTopicItem.topicItem;
//
//        List<TopicItem> topicItems = findTopicItemByTopic(topic);
//
//        JPAQuery<Item> query = queryFactory.selectFrom(item)
//                .leftJoin(topicItem.items, item)
//                .fetchJoin()
//                .where(
//                        this.equals(topicItem.topic, topic)
//                        , this.containsIgnoreCase(topicItem.title, search)
//                )
//                .orderBy(topicItem.ord.asc());
//
//        JPQLQuery<Item> jpqlQuery = getQuerydsl().applyPagination(pageable, query);
//        QueryResults<Item> fetchResults = jpqlQuery.fetchResults();
//        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
//    }

    private List<TopicItem> findTopicItemByTopic(Topic topic) {
        QTopicItem topicItem = QTopicItem.topicItem;

        JPQLQuery<TopicItem> query = from(topicItem);
        query
                .where(
                        this.equals(topicItem.topic, topic)
                        , this.equals(topicItem.status, TopicItemStatus.ACTIVATE)
                );

        return query.fetch();
    }
}
