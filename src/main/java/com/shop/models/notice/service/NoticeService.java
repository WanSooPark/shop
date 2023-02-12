package com.shop.models.notice.service;

import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.models.categories.domain.Category;
import com.shop.models.items.domain.Item;
import com.shop.models.items.infra.repo.ItemRepository;
import com.shop.models.notice.domain.Notice;
import com.shop.models.notice.infra.repo.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository repository;

    public Page<Notice> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Notice> search(String search, Pageable pageable) {
        return repository.findByTitleContainsOrContentContains(search, search, pageable);
    }

    public Notice add(Notice item) {
        return repository.save(item);
    }

    public Notice update(Notice item) {
        return repository.save(item);
    }

    public void delete(List<Long> ids) {
        repository.deleteByIdIn(ids);
    }

    public Notice findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("유효하지 않은 공지사항 id 입니다."));
    }

    public void updateViewCountById(Long id) {
        repository.updateViewCountById(id);
    }
}
