package com.shop.services.service.carts.service;//package com.shop.services.service.carts.service;
//
//import com.shop.models.carts.domain.Cart;
//import com.shop.models.carts.domain.CartItem;
//import com.shop.models.carts.infra.repo.CartItemRepository;
//import com.shop.models.carts.infra.repo.CartRepository;
//import com.shop.models.items.domain.Item;
//import com.shop.models.items.infra.repo.ItemRepository;
//import com.shop.models.members.domain.Member;
//import com.shop.models.members.infra.repo.MemberRepository;
//import com.shop.services.service.carts.dto.CartDetailDto;
//import com.shop.services.service.carts.dto.CartItemDto;
//import com.shop.services.service.carts.dto.CartOrderDto;
//import com.shop.services.service.orders.dto.OrderDto;
//import com.shop.services.service.orders.service.OrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.thymeleaf.util.StringUtils;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class CartService {
//
//    private final ItemRepository itemRepository;
//    private final MemberRepository memberRepository;
//    private final CartRepository cartRepository;
//    private final CartItemRepository cartItemRepository;
////    private final OrderService orderService;
//
//    public Long addCart(CartItemDto cartItemDto, String email) {
//
//        Item item = itemRepository.findById(cartItemDto.getItemId())
//                .orElseThrow(EntityNotFoundException::new);
//        Member member = memberRepository.findByEmail(email);
//
//        Cart cart = cartRepository.findByMemberId(member.getId());
//        if (cart == null) {
//            cart = Cart.createCart(member);
//            cartRepository.save(cart);
//        }
//
//        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
//
//        if (savedCartItem != null) {
//            savedCartItem.addCount(cartItemDto.getCount());
//            return savedCartItem.getId();
//        } else {
//            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
//            cartItemRepository.save(cartItem);
//            return cartItem.getId();
//        }
//    }
//
//    @Transactional(readOnly = true)
//    public List<CartDetailDto> getCartList(String email) {
//
//        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();
//
//        Member member = memberRepository.findByEmail(email);
//        Cart cart = cartRepository.findByMemberId(member.getId());
//        if (cart == null) {
//            return cartDetailDtoList;
//        }
//
////        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
//        cartDetailDtoList = null;
//        return cartDetailDtoList;
//    }
//
//    @Transactional(readOnly = true)
//    public boolean validateCartItem(Long cartItemId, String email) {
//        Member curMember = memberRepository.findByEmail(email);
//        CartItem cartItem = cartItemRepository.findById(cartItemId)
//                .orElseThrow(EntityNotFoundException::new);
//        Member savedMember = cartItem.getCart()
//                .getMember();
//
//        return StringUtils.equals(curMember.getEmail(), savedMember.getEmail());
//    }
//
//    public void updateCartItemCount(Long cartItemId, int count) {
//        CartItem cartItem = cartItemRepository.findById(cartItemId)
//                .orElseThrow(EntityNotFoundException::new);
//
//        cartItem.updateCount(count);
//    }
//
//    public void deleteCartItem(Long cartItemId) {
//        CartItem cartItem = cartItemRepository.findById(cartItemId)
//                .orElseThrow(EntityNotFoundException::new);
//        cartItemRepository.delete(cartItem);
//    }
//
//    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email) {
//        List<OrderDto> orderDtoList = new ArrayList<>();
//
//        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
//            CartItem cartItem = cartItemRepository
//                    .findById(cartOrderDto.getCartItemId())
//                    .orElseThrow(EntityNotFoundException::new);
//
//            OrderDto orderDto = new OrderDto();
//            orderDto.setItemId(cartItem.getItem()
//                    .getId());
//            orderDto.setCount(cartItem.getCount());
//            orderDtoList.add(orderDto);
//        }
//
//        Long orderId = orderService.orders(orderDtoList, email);
//        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
//            CartItem cartItem = cartItemRepository
//                    .findById(cartOrderDto.getCartItemId())
//                    .orElseThrow(EntityNotFoundException::new);
//            cartItemRepository.delete(cartItem);
//        }
//
//        return orderId;
//    }
//
//}