package com.allddaom.models.badges.service;

import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.models.badges.domain.Badge;
import com.allddaom.models.badges.infra.repo.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
        if (ObjectUtils.isEmpty(badgesString)) {
            return null;
        }
        return repository.findByCodeIn(badgesString);
    }

}
