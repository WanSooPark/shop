package com.allddaom.commons.entity;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Pagination response base dto
 * 기존 Page 정보들이 지저분해서 정리 용도
 */
public class BasePage<T> {

    private final Page<T> page;

    public BasePage(Page<T> page) {
        this.page = page;
    }

    public List<T> getContent() {
        return page.getContent();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public long getTotalContents() {
        return page.getTotalElements();
    }

    public boolean isFirstPage() {
        return page.isFirst();
    }

    public boolean isLastPage() {
        return page.isLast();
    }

    public boolean hasContent() {
        return page.hasContent();
    }

    public int getPage() {
        return page.getNumber();
    }

    public int getSize() {
        return page.getSize();
    }
}
