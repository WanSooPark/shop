package com.allddaom.models.items.infra.repo;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.allddaom.commons.querydsl.CustomQuerydslRepositorySupport;
import com.allddaom.models.categories.domain.Category;
import com.allddaom.models.categories.domain.QCategory;
import com.allddaom.models.items.domain.Item;
import com.allddaom.models.items.domain.QItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class QDSLItemRepositoryImpl extends CustomQuerydslRepositorySupport implements QDSLItemRepository {

    public QDSLItemRepositoryImpl() {
        super(Item.class);
    }

    @Override
    public Page<Item> searchForAdmin(String searchType, String search, Long categoryId, Pageable pageable) {
        QItem item = QItem.item;

        JPQLQuery<Item> query = from(item);
        query
                .where(
                        this.search(item, searchType, search)
                        , this.equals(item.category.id, categoryId)
                        , this.containsIgnoreCase(item.name, search)
                );

        JPQLQuery<Item> jpqlQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Item> fetchResults = jpqlQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    @Override
    public Page<Item> search(Long categoryId, String search, Pageable pageable) {
        QItem item = QItem.item;

        Category category = findCategoryById(categoryId);
        Set<Long> categoryIds = subCategories(category); // 하위 카테고리 id 조회

        JPQLQuery<Item> query = from(item);
        query
                .where(
                        this.in(item.category.id, categoryIds)
                        , this.containsIgnoreCase(item.name, search)
                );

        JPQLQuery<Item> jpqlQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Item> fetchResults = jpqlQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    private Set<Long> subCategories(Category category) {
        Set<Long> categoryIds = new HashSet<>();
        categoryIds.add(category.getId());
        if (category.getDepth() != 3) {
            List<Category> subCategories = findSubCategoriesByCategory(category);
            subCategories.forEach(subCategory -> {
                categoryIds.addAll(subCategories(subCategory));
            });
        }

        return categoryIds;
    }

    /**
     * 카테고리 조회
     */
    public Category findCategoryById(Long categoryId) {
        QCategory category = QCategory.category;

        JPQLQuery<Category> query = from(category);
        query
                .where(
                        this.equals(category.id, categoryId)
                );

        return query.fetchOne();
    }

    /**
     * 하위 카테고리 조회
     */
    public List<Category> findSubCategoriesByCategory(Category topCategory) {
        QCategory category = QCategory.category;

        JPQLQuery<Category> query = from(category);
        query
                .where(
                        this.equals(category.topCategory.id, topCategory.getId())
                );

        return query.fetch();
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
