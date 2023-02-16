package com.allddaom.models.items.service;

import com.allddaom.models.items.domain.ItemOptionBuilder;
import com.allddaom.models.items.infra.repo.ItemOptionBuilderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemOptionBuilderService {

    private final ItemOptionBuilderRepository repository;

    public ItemOptionBuilder add(ItemOptionBuilder itemOptionBuilder) {
        return repository.save(itemOptionBuilder);
    }

    public void deleteAll(List<ItemOptionBuilder> optionBuilders) {
        repository.deleteAll(optionBuilders);
    }

}
