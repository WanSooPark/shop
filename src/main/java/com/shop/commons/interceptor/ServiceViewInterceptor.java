package com.shop.commons.interceptor;

import com.shop.commons.interceptor.service.ServiceInterceptorCategoryService;
import com.shop.services.service.categories.dto.search.ServiceCategorySearch;
import com.shop.services.service.categories.dto.search.ServiceCategorySearchResponse;
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
public class ServiceViewInterceptor implements HandlerInterceptor {

    private final ServiceInterceptorCategoryService serviceInterceptorCategoryService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(request.getRequestURI());
        // 뷰 렌더링 전, 핸들러 처리 이후
        if (!ObjectUtils.isEmpty(modelAndView)) {
            ServiceCategorySearch searchDto = new ServiceCategorySearch();
            List<ServiceCategorySearchResponse> categories = serviceInterceptorCategoryService.categoriesSearch(searchDto);
            if (!ObjectUtils.isEmpty(categories)) {
                modelAndView.addObject("categories", categories);
            }
        }

    }
}
