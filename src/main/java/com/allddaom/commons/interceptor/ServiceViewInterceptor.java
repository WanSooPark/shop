package com.allddaom.commons.interceptor;

import com.allddaom.commons.interceptor.dto.InterceptorTopicResponse;
import com.allddaom.commons.interceptor.service.ServiceInterceptorCartService;
import com.allddaom.commons.interceptor.service.ServiceInterceptorCategoryService;
import com.allddaom.commons.interceptor.service.ServiceInterceptorTopicService;
import com.allddaom.commons.security.PrincipalDetails;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.categories.dto.search.ServiceCategorySearch;
import com.allddaom.services.service.categories.dto.search.ServiceCategorySearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ServiceViewInterceptor implements HandlerInterceptor {

    private final ServiceInterceptorCategoryService serviceInterceptorCategoryService;
    private final ServiceInterceptorTopicService serviceInterceptorTopicService;
    private final ServiceInterceptorCartService serviceInterceptorCartService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 뷰 렌더링 전, 핸들러 처리 이후
        if (!ObjectUtils.isEmpty(modelAndView)) {
            addCategories(modelAndView);
            addTopicMenus(modelAndView);
            addCartCount(modelAndView, request);
        }
    }

    private void addCategories(ModelAndView modelAndView) {
        ServiceCategorySearch searchDto = new ServiceCategorySearch();
        List<ServiceCategorySearchResponse> categories = serviceInterceptorCategoryService.categoriesSearch(searchDto);
        if (!ObjectUtils.isEmpty(categories)) {
            modelAndView.addObject("categories", categories);
        }
    }

    private void addTopicMenus(ModelAndView modelAndView) {
        List<InterceptorTopicResponse> topics = serviceInterceptorTopicService.getActivateTopicMenus();
        if (!ObjectUtils.isEmpty(topics)) {
            modelAndView.addObject("topics", topics);
        }
    }

    private void addCartCount(ModelAndView modelAndView, HttpServletRequest request) {
        Long cartItemCount = 0L;

        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof PrincipalDetails) {
            PrincipalDetails principalDetails = (PrincipalDetails) principal;
            Member member = principalDetails.getMember();

            cartItemCount = serviceInterceptorCartService.getCartItemCountByMember(member);
        } else {
            HttpSession session = request.getSession();
            cartItemCount = serviceInterceptorCartService.getCartItemCountBySessionId(session.getId());
        }

        if (!ObjectUtils.isEmpty(cartItemCount)) {
            modelAndView.addObject("cartItemCount", cartItemCount);
        }
    }
}
