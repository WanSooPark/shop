package com.shop.models.items.infra.repo;

import com.shop.models.items.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, QDSLItemRepository {
}
