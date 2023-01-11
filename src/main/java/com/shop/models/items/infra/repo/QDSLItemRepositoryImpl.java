package com.shop.models.items.infra.repo;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.shop.commons.querydsl.CustomQuerydslRepositorySupport;
import com.shop.models.items.domain.Item;
import com.shop.models.items.domain.QItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public class QDSLItemRepositoryImpl extends CustomQuerydslRepositorySupport implements QDSLItemRepository {

    public QDSLItemRepositoryImpl() {
        super(Item.class);
    }

    @Override
    public Page<Item> searchForAdmin(String searchType, String search, String categoryId, Pageable pageable) {
        QItem item = QItem.item;

        JPQLQuery<Item> query = from(item);
        query
                .where(
                        this.search(item, searchType, search)
                        , this.equals(item.category.id, categoryId)
                );

        JPQLQuery<Item> jpqlQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Item> fetchResults = jpqlQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    /**
     * search Type 으로 분기
     */
    private BooleanExpression search(QItem item, String searchType, String search) {
        if (!ObjectUtils.isEmpty(searchType) && searchType.equals("name")) {
            return this.containsIgnoreCase(item.name, search);
        }
        return null;
    }

}
