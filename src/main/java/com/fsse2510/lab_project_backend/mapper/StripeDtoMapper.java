package com.fsse2510.lab_project_backend.mapper;

import com.fsse2510.lab_project_backend.data.Stripe.domainObject.response.StripeResponseData;
import com.fsse2510.lab_project_backend.data.Stripe.dto.response.StripeResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StripeDtoMapper {
    public StripeResponseDto toStripeResponseDto(StripeResponseData stripeResponseData){
        StripeResponseDto stripeResponseDto = new StripeResponseDto();
        stripeResponseDto.setUrl(stripeResponseData.getUrl());
        return stripeResponseDto;
    }
}
