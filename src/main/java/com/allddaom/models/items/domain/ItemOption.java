package com.allddaom.models.items.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;
    private Long stock;
    private Long stockNotificationQuantity; // 통보 재고 수량
    @Enumerated(EnumType.STRING)
    private ItemOptionStatus status;

}
