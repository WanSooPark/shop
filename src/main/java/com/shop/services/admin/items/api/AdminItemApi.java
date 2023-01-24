package com.shop.services.admin.items.api;

import com.shop.services.admin.items.dto.search.AdminItemSearchDto;
import com.shop.services.admin.items.service.AdminItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/items")
@RequiredArgsConstructor
public class AdminItemApi {

    private final AdminItemService adminItemService;

    @GetMapping
    public ResponseEntity<?> itemSearch(@Valid AdminItemSearchDto.Request searchDto, @PageableDefault Pageable pageable) {
        AdminItemSearchDto.Response response = adminItemService.search(searchDto, pageable);
        return ResponseEntity.ok(response);
    }

}
