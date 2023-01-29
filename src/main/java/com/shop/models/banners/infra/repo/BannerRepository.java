package com.shop.models.banners.infra.repo;

import com.shop.models.banners.domain.Banner;
import com.shop.models.banners.domain.BannerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    List<Banner> findByStatus(BannerStatus status);
}
