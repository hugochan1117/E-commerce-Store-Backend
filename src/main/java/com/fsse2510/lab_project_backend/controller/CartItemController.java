package com.fsse2510.lab_project_backend.controller;

import com.fsse2510.lab_project_backend.config.EnvConfig;
import com.fsse2510.lab_project_backend.data.cart_item.dto.response.CartItemResponseDto;
import com.fsse2510.lab_project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2510.lab_project_backend.mapper.CartItemDtoMapper;
import com.fsse2510.lab_project_backend.mapper.UserDataMapper;
import com.fsse2510.lab_project_backend.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PROD_BASE_URL})
@RequestMapping("/cart/items")
public class CartItemController {
    private final UserDataMapper userDataMapper;
    private final CartItemService cartItemService;
    private final CartItemDtoMapper cartItemDtoMapper;

    public CartItemController(UserDataMapper userDataMapper, CartItemService cartItemService, CartItemDtoMapper cartItemDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.cartItemService = cartItemService;
        this.cartItemDtoMapper = cartItemDtoMapper;
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{pid}/{quantity}")
    public void putItemInCart(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid, @PathVariable Integer quantity){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.putItemInCart(firebaseUserData, pid, quantity);
    }

    @GetMapping
    public List<CartItemResponseDto> getUserCart(@AuthenticationPrincipal Jwt jwt){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        return cartItemDtoMapper.toCartItemResponseDtoList(cartItemService.getUserCart(firebaseUserData));
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{pid}/{quantity}")
    public void updateCartQuantity(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid, @PathVariable Integer quantity){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.updateCartQuantity(firebaseUserData, pid, quantity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{pid}")
    public void deleteCartProduct(@AuthenticationPrincipal Jwt jwt,@PathVariable Integer pid){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.deleteCartProduct(firebaseUserData, pid);
    }



}
