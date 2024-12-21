package com.dearnote.validation.validator;

import com.dearnote.apipayload.code.status.ErrorStatus;
import com.dearnote.repository.LetterRepository;
import com.dearnote.validation.annotation.ExistLetter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LetterExistValidator implements ConstraintValidator<ExistLetter, Long> {

    private final LetterRepository letterRepository;

    @Override
    public void initialize(ExistLetter constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        // 편지 id가 null이거나, 레포지토리에 존재하지 않으면 에러
        if (value == null || !letterRepository.existsById(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.LETTER_NOT_FOUND.toString())
                    .addConstraintViolation();
            return false;
        }

        return true; // 검증 성공
    }
}