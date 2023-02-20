package com.allddaom.models.topics.infra.repo;

import com.allddaom.commons.querydsl.CustomQuerydslRepositorySupport;
import com.allddaom.models.topics.domain.QTopic;
import com.allddaom.models.topics.domain.Topic;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class QDSLTopicRepositoryImpl extends CustomQuerydslRepositorySupport implements QDSLTopicRepository {

    public QDSLTopicRepositoryImpl() {
        super(Topic.class);
    }

    @Override
    public Page<Topic> searchForAdmin(String search, Pageable pageable) {
        QTopic topic = QTopic.topic;

        JPQLQuery<Topic> query = from(topic);
//        query
//                .where(
//                );

        JPQLQuery<Topic> jpqlQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Topic> fetchResults = jpqlQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
