package com.allddaom.models.items.infra.repo;

import com.allddaom.models.items.domain.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
}
