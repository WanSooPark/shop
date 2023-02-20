package com.allddaom.models.items.domain;

import com.allddaom.commons.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ItemImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 이미지 파일명

    private String originName; // 원본 이미지 파일명

    private String url; // 이미지 조회 경로

    private boolean representative; // 대표 이미지 여부

}
