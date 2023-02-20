package com.allddaom.models.categories.infra.repo;

import com.allddaom.commons.querydsl.CustomQuerydslRepositorySupport;
import com.allddaom.models.categories.domain.Category;
import com.allddaom.models.categories.domain.QCategory;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class QDSLCategoryRepositoryImpl extends CustomQuerydslRepositorySupport implements QDSLCategoryRepository {

    public QDSLCategoryRepositoryImpl() {
        super(Category.class);
    }

    @Override
    public Page<Category> searchForAdmin(String search, Pageable pageable) {
        QCategory category = QCategory.category;

        JPQLQuery<Category> query = from(category);
        query
                .where(
                        this.containsIgnoreCase(category.name, search)
                );

        JPQLQuery<Category> jpqlQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Category> fetchResults = jpqlQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
