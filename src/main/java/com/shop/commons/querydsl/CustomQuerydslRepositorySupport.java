package com.shop.commons.querydsl;

import com.querydsl.core.types.dsl.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Custom QueryDSl Support
 * <p>
 * where 절 구현
 */
public class CustomQuerydslRepositorySupport extends QuerydslRepositorySupport {

    public CustomQuerydslRepositorySupport(Class<?> domainClass) {
        super(domainClass);
    }

    protected BooleanExpression in(EnumPath enumPath, Object[] values) {
        if (!ObjectUtils.isEmpty(values)) {
            return enumPath.in(values);
        }
        return null;
    }

    protected BooleanExpression in(EntityPathBase requester, List requesters) {
        return requester.in(requesters);
    }

    protected BooleanExpression in(EntityPathBase requester, Set requesters) {
        return requester.in(requesters);
    }

    protected BooleanExpression in(StringPath name, String[] values) {
        if (ObjectUtils.isEmpty(values) || values.length == 0) {
            return null;
        }
        return name.in(values);
    }

    protected BooleanExpression in(NumberPath numberPath, Collection values) {
        if (ObjectUtils.isEmpty(values) || values.size() == 0) {
            return null;
        }
        return numberPath.in(values);
    }

    protected BooleanExpression equals(EnumPath enumPath, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return enumPath.eq(value);
        }
        return null;
    }

    protected BooleanExpression equals(StringPath path, String value) {
        if (!ObjectUtils.isEmpty(value)) {
            return path.eq(value);
        }
        return null;
    }

    protected BooleanExpression equals(NumberPath numberPath, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return numberPath.eq(value);
        }
        return null;
    }

    protected BooleanExpression equals(EntityPathBase entityPathBase, Object object) {
        if (!ObjectUtils.isEmpty(object)) {
            return entityPathBase.eq(object);
        }
        return null;
    }

    protected BooleanExpression equals(DatePath<LocalDate> date, LocalDate targetDate) {
        if (!ObjectUtils.isEmpty(targetDate)) {
            return date.eq(targetDate);
        }
        return null;
    }

    protected BooleanExpression containsIgnoreCase(StringPath path, String value) {
        if (!Objects.isNull(value)) {
            return path.containsIgnoreCase(value);
        }
        return null;
    }

    protected BooleanExpression containsIgnoreCase(StringTemplate stringTemplate, String value) {
        if (!Objects.isNull(value)) {
            return stringTemplate.containsIgnoreCase(value);
        }
        return null;
    }

    protected BooleanExpression between(DateTimePath<LocalDateTime> createdDateTime, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return createdDateTime.between(
                Objects.isNull(startDateTime) ? LocalDateTime.MIN : startDateTime
                , Objects.isNull(endDateTime) ? LocalDateTime.now() : endDateTime);
    }

    protected BooleanExpression between(DatePath<LocalDate> targetDate, LocalDate startDate, LocalDate endDate) {
        return targetDate.between(startDate, endDate);
    }

    protected BooleanExpression notEquals(EnumPath enumPath, Object value) {
        if (!ObjectUtils.isEmpty(value)) {
            return enumPath.ne(value);
        }
        return null;
    }
}
