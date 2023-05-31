package com.deyvidsantos.emailsender.registration;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String arg0) {
        // TODO: Expressão regular para o e-mail
        return true;
    }
    
}
