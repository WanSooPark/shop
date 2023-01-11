package com.shop.models.items.infra.repo;

import com.shop.models.items.domain.ItemOptionBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionBuilderRepository extends JpaRepository<ItemOptionBuilder, Long> {
}
