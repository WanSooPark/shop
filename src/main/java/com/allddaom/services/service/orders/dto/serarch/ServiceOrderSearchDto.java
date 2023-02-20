package com.allddaom.services.service.orders.dto.serarch;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.services.service.orders.dto.ServiceOrderResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ServiceOrderSearchDto {

    @Data
    public static class Request {
        private String statusGroup; // valid, invalid
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate startDate;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate endDate;

        public LocalDate getStartDate() {
            if (ObjectUtils.isEmpty(this.startDate)) {
                this.startDate = LocalDate.now()
                        .minusMonths(3);
            }
            return this.startDate;
        }

        public LocalDate getEndDate() {
            if (ObjectUtils.isEmpty(this.endDate)) {
                this.endDate = LocalDate.now();
            }
            return this.endDate;
        }

        public LocalDateTime getStartDateTime() {
            LocalDate startDate1 = this.getStartDate();
            return LocalDateTime.of(startDate1, LocalTime.MIN);
        }

        public LocalDateTime getEndDateTime() {
            LocalDate endDate1 = this.getEndDate();
            return LocalDateTime.of(endDate.withDayOfMonth(endDate1.lengthOfMonth()), LocalTime.MAX);
        }

    }

    @Data
    @Builder
    public static class Response {
        private BasePage<ServiceOrderResponse> orderPage;
    }

}
