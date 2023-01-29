package com.shop.models.items.service;

import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.models.items.domain.Item;
import com.shop.models.items.infra.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public Item add(Item item) {
        return repository.save(item);
    }

    public Page<Item> searchForAdmin(String searchType, String search, Long categoryId, Pageable pageable) {
        return repository.searchForAdmin(searchType, search, categoryId, pageable);
    }

    public Item update(Item item) {
        return repository.save(item);
    }

    public Item findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("유효하지 않은 상품 id 입니다."));
    }

    public Page<Item> searchByCategory(Long categoryId, String search, Pageable pageable) {
        return repository.search(categoryId, search, pageable);
    }

    public List<Item> findByIdIn(List<Long> itemIds) {
        return repository.findByIdIn(itemIds);
    }

    public List<Item> findByRecBadge(boolean recBadge) {
        return repository.findByRecBadge(recBadge);
    }

    public List<Item> findByNewBadge(boolean newBadge) {
        return repository.findByNewBadge(newBadge);
    }
}
