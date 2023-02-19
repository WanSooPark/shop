package com.allddaom.services.service.itemreviews.service;

import com.allddaom.models.itemreviews.service.ItemReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceItemReviewService {

    private final ItemReviewService itemReviewService;
}
