package com.allddaom.commons.interceptor.service;

import com.allddaom.commons.interceptor.dto.InterceptorTopicItemResponse;
import com.allddaom.commons.interceptor.dto.InterceptorTopicResponse;
import com.allddaom.models.carts.service.CartItemService;
import com.allddaom.models.members.domain.Member;
import com.allddaom.models.topics.domain.Topic;
import com.allddaom.models.topics.domain.TopicItem;
import com.allddaom.models.topics.domain.TopicItemStatus;
import com.allddaom.models.topics.domain.TopicStatus;
import com.allddaom.models.topics.service.TopicItemService;
import com.allddaom.models.topics.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceInterceptorCartService {

    private final CartItemService cartItemService;

    public Long getCartItemCountByMember(Member member) {
        return cartItemService.countByMember(member);
    }

    public Long getCartItemCountBySessionId(String sessionId) {
        return cartItemService.countBySessionId(sessionId);
    }
}
