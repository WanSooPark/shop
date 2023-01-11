package com.shop.models.badges.service;

import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.models.badges.domain.Badge;
import com.shop.models.badges.infra.repo.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository repository;

    public Badge findByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new NoContentException("유효하지 않은 뱃지 code 입니다."));
    }

    public List<Badge> findByCodeIn(List<String> badgesString) {
        return repository.findByCodeIn(badgesString);
    }

}
