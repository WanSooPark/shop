package com.shop.models.items.infra.repo;

import com.shop.models.items.domain.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
}
