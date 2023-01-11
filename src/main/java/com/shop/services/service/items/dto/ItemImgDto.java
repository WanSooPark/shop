//package com.shop.services.service.items.dto;
//
//import com.shop.models.items.domain.ItemImage;
//import lombok.Getter;
//import lombok.Setter;
//import org.modelmapper.ModelMapper;
//
//@Getter
//@Setter
//public class ItemImgDto {
//
//    private static ModelMapper modelMapper = new ModelMapper();
//    private Long id;
//    private String imgName;
//    private String oriImgName;
//    private String imgUrl;
//    private String repImgYn;
//
//    public static ItemImgDto of(ItemImage itemImage) {
//        return modelMapper.map(itemImage, ItemImgDto.class);
//    }
//
//}
