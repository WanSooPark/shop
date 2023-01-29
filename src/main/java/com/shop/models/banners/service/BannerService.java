package com.shop.models.banners.service;

import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.models.banners.domain.Banner;
import com.shop.models.banners.domain.BannerStatus;
import com.shop.models.banners.infra.repo.BannerRepository;
import com.shop.services.admin.banners.dto.search.AdminBannerSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository repository;

    public Page<Banner> searchForAdmin(AdminBannerSearchDto.Request dto, Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Banner findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("유효하지 않은 banner id 입니다."));
    }

    public Banner add(Banner banner) {
        return repository.save(banner);
    }

    public List<Banner> findByStatus(BannerStatus status) {
        return repository.findByStatus(status);
    }
}
