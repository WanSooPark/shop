package com.allddaom.services.admin.topics.dto.form;

import lombok.Data;

public class AdminTopicItemFormDto {

    @Data
    public static class Request {
        private Long id;
        private String search;
    }

}
