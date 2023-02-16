package com.allddaom.models.items.infra.repo;

import com.allddaom.models.categories.domain.Category;
import com.allddaom.models.items.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QDSLItemRepository {
    List<Item> findByIdIn(List<Long> itemIds);

    List<Item> findByRecBadge(boolean recBadge);

    List<Item> findByNewBadge(boolean newBadge);

    List<Item> findByCategory(Category category);
}
