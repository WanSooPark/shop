package com.allddaom.models.banners.infra.repo;

import com.allddaom.models.banners.domain.Banner;
import com.allddaom.models.banners.domain.BannerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    List<Banner> findByStatus(BannerStatus status);
}
