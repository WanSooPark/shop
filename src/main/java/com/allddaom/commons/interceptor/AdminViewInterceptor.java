package com.allddaom.commons.interceptor;

import com.allddaom.commons.interceptor.dto.InterceptorTopicResponse;
import com.allddaom.commons.interceptor.service.AdminInterceptorTopicItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminViewInterceptor implements HandlerInterceptor {

    private final AdminInterceptorTopicItemService adminInterceptorTopicItemService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 뷰 렌더링 전, 핸들러 처리 이후
        if (!ObjectUtils.isEmpty(modelAndView)) {
            addTopicMenus(modelAndView);
        }
    }

    private void addTopicMenus(ModelAndView modelAndView) {
        List<InterceptorTopicResponse> topicMenus = adminInterceptorTopicItemService.getActivateTopicMenus();
        if (!ObjectUtils.isEmpty(topicMenus)) {
            modelAndView.addObject("topicMenus", topicMenus);
        }
    }
}
