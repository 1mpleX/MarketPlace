package com.market_place.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckEmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckEmail {

    String[] value() default {"@gmail.com", "@yahoo.com", "@hotmail.com", "@outlook.com", "@aol.com",
            "@icloud.com", "@mail.com", "@yandex.ru", "@mail.ru", "@inbox.ru",
            "@list.ru", "@bk.ru", "@gmx.com", "@protonmail.com", "@zoho.com",
            "@live.com", "@me.com", "@qq.com", "@163.com", "@sina.com"};

    String message() default "Email should be @xxx.xxx (@gmail.com, @yahoo.com, etc.)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
