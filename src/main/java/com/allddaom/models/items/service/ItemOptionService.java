package com.allddaom.models.items.service;

import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.models.items.domain.ItemOption;
import com.allddaom.models.items.infra.repo.ItemOptionRepository;
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

    public ItemOption findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoContentException("유효하지 않은 옵션 id 입니다."));
    }
}
