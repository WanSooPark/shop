package com.allddaom.services.service.topics.infra.repo;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.allddaom.commons.querydsl.CustomQuerydslRepositorySupport;
import com.allddaom.models.items.domain.Item;
import com.allddaom.models.topics.domain.QTopicItem;
import com.allddaom.models.topics.domain.Topic;
import com.allddaom.models.topics.domain.TopicItem;
import com.allddaom.models.topics.domain.TopicItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
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
