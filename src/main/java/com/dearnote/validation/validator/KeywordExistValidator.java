package com.dearnote.validation.validator;

import com.dearnote.apiPayload.code.status.ErrorStatus;
import com.dearnote.repository.KeywordRepository;
import com.dearnote.validation.annotation.ExistKeyword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeywordExistValidator implements ConstraintValidator<ExistKeyword, Long> {

    private final KeywordRepository keywordRepository;

    @Override
    public void initialize(ExistKeyword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        // 회원 id가 null이거나, 레포지토리에 존재하지 않으면 에러
        if (value == null || !keywordRepository.existsById(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString())
                    .addConstraintViolation();
            return false;
        }

        return true; // 검증 성공
    }
}