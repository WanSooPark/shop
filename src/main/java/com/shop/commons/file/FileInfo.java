package com.shop.commons.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileInfo {
    private String originName = ""; // 원본 이미지 명
    private String name = ""; // 저장한 이미지 명
    private String url = ""; // 이미지 주소
}
