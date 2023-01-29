package com.shop.commons.init;

import com.shop.models.categories.domain.Category;
import com.shop.models.categories.domain.CategoryStatus;
import com.shop.models.categories.service.CategoryService;
import com.shop.models.members.domain.Member;
import com.shop.models.members.domain.MemberStatus;
import com.shop.models.members.domain.Role;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicStatus;
import com.shop.models.topics.service.TopicService;
import com.shop.services.service.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class AppStartRunner implements ApplicationRunner {

    private final MemberService memberService;
    private final CategoryService categoryService;
    private final TopicService topicService;

    private final PasswordEncoder passwordEncoder;

    @Value("${init_data}")
    private boolean initData;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (initData) { // properties 에 true 로 설정해두면 기본 데이터 초기화
            Member testMember = getMember("rn00n@naver.com", "테스트계정", "1q2w3e4r!", "주소");

            Category category1 = getCategory("가공식품", null);
            Category category2 = getCategory("농수축산물", null);
            Category category3 = getCategory("배달용품", null);
            Category category1_1 = getCategory("가공식품-1", category1);
            Category category1_2 = getCategory("가공식품-2", category1);
            Category category1_1_1 = getCategory("가공식품-1-1", category1_1);
            Category category2_1 = getCategory("농수축산물-1", category2);
            Category category2_1_1 = getCategory("농수축산물-1-1", category2_1);
            Category category3_1 = getCategory("배달용품-1", category3);
            Category category3_1_1 = getCategory("배달용품-1-1", category3_1);

            Long topicCount = topicService.count();
            Topic topicNew = getTopic("NEW", "신상품", ++topicCount);
            Topic topicDaySale = getTopic("TODAY_SALE", "하루특가", ++topicCount);
        }
    }

    private Topic getTopic(String code, String name, Long topicCount) {
        Topic topic = topicService.findByCode(code);
        if (ObjectUtils.isEmpty(topic)) {
            topic = new Topic();
            topic.setCode(code);
            topic.setName(name);
            topic.setOrd(topicCount);
            topic.setStatus(TopicStatus.ACTIVATE);
            topic = topicService.save(topic);
        }
        return topic;
    }

    private Member getMember(String username, String name, String password, String address) {
        Member member = memberService.findByUsername(username);
        if (ObjectUtils.isEmpty(member)) {
            member = new Member();
            member.setName(name);
            member.setEmail(username);
            member.setUsername(username);
            member.setPassword(passwordEncoder.encode(password));
            member.setAddress(address);

            member.setNickname("nickname " + name);
            member.setAdministrativeNotes("");
            member.setTel("");
            member.setStatus(MemberStatus.NORMAL);
            member.setRole(Role.USER);
            member = memberService.add(member);
        }
        return member;
    }

    private Category getCategory(String name, Category topCategory) {
        Category category = categoryService.findByNameAndTopCategory(name, topCategory);
        if (ObjectUtils.isEmpty(category)) {
            category = new Category();
            category.setName(name);
            category.setTopCategory(topCategory);
            category.setStatus(CategoryStatus.EXPOSE);
            category = categoryService.add(category);
        }
        return category;
    }
}
