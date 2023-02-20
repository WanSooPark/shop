package com.allddaom.services.service.faq.service;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.models.faq.domain.Faq;
import com.allddaom.models.faq.service.FaqService;
import com.allddaom.models.notice.domain.Notice;
import com.allddaom.services.service.faq.dto.form.ServiceNoticeForm;
import com.allddaom.services.service.faq.dto.search.ServiceFaqSearchDto;
import com.allddaom.services.service.faq.dto.search.ServiceFaqSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceFaqService {

   private final FaqService faqService;

   public List<Faq> findAll() {
      return faqService.findAll();
   }
}
