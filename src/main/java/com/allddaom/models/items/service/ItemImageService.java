package com.allddaom.models.items.service;

import com.allddaom.models.items.domain.ItemImage;
import com.allddaom.models.items.infra.repo.ItemImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemImageService {

    private final ItemImageRepository repository;

    public ItemImage add(ItemImage itemImage) {
        return repository.save(itemImage);
    }

}
