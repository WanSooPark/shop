package com.allddaom.models.banners.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text1;

    private String text2;

    private String text3;

    private String text4;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private BannerStatus status;

    public void update(String text1, String text2, String text3, String text4, String imageUrl, BannerStatus status) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.imageUrl = imageUrl;
        this.status = status;
    }
}
