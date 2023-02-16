package com.allddaom.models.items.infra.repo;

import com.allddaom.models.items.domain.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
}
