package com.shop.models.topics.infra.repo;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.shop.commons.querydsl.CustomQuerydslRepositorySupport;
import com.shop.models.topics.domain.QTopicItem;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicItem;
import com.shop.models.topics.domain.TopicItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class QDSLTopicItemRepositoryImpl extends CustomQuerydslRepositorySupport implements QDSLTopicItemRepository {

    public QDSLTopicItemRepositoryImpl() {
        super(TopicItem.class);
    }

    @Override
    public Page<TopicItem> searchForAdmin(Topic topic, TopicItemStatus status, String search, Pageable pageable) {
        QTopicItem topicItem = QTopicItem.topicItem;

        JPQLQuery<TopicItem> query = from(topicItem);
        query
                .where(
                        this.equals(topicItem.topic, topic)
                        , this.equals(topicItem.status, status)
                        , this.containsIgnoreCase(topicItem.title, search)
                );

        JPQLQuery<TopicItem> jpqlQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<TopicItem> fetchResults = jpqlQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
