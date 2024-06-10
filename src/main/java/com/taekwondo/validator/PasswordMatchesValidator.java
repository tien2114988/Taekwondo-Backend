//package com.taekwondo.validator;
//
//import com.taekwondo.annotation.PasswordMatches;
//import com.taekwondo.dto.user.CreateUserDto;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
//    @Override
//    public void initialize(PasswordMatches constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(Object obj, ConstraintValidatorContext context){
//        CreateUserDto user = (CreateUserDto) obj;
//        return user.getPassword().equals(user.getMatchingPassword());
//    }
//}
//
