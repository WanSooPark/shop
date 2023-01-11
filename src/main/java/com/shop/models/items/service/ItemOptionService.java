package com.shop.models.items.service;

import com.shop.models.items.domain.ItemOption;
import com.shop.models.items.infra.repo.ItemOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemOptionService {

    private final ItemOptionRepository repository;

    public ItemOption add(ItemOption itemOption) {
        return repository.save(itemOption);
    }

    public void deleteAll(List<ItemOption> options) {
        repository.deleteAll(options);
    }

}
