package com.shop.models.badges.infra.repo;

import com.shop.models.badges.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    Optional<Badge> findByCode(String code);

    List<Badge> findByCodeIn(List<String> badgesString);
}
