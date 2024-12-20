package com.dearnote.validation.validator;

import com.dearnote.apipayload.code.status.ErrorStatus;
import com.dearnote.validation.annotation.CheckPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer>{
    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        // 페이지가 0 이하이거나 널인 경우 유효성 검증을 실패로 처리
        if (page == null || page < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NUMBER_INVALID.toString()).addConstraintViolation();
            return false;
        }


        return true;
    }
}
