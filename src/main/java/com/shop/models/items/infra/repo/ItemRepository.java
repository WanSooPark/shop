package com.shop.models.items.infra.repo;

import com.shop.models.items.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QDSLItemRepository {
    List<Item> findByIdIn(List<Long> itemIds);
}
