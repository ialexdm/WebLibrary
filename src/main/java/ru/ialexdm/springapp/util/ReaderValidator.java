package ru.ialexdm.springapp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ialexdm.springapp.models.Reader;
import ru.ialexdm.springapp.services.ReadersService;

@Component
public class ReaderValidator implements Validator {
    private final ReadersService readersService;

    public ReaderValidator(ReadersService readersService) {
        this.readersService = readersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Reader.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Reader reader = (Reader) target;
        if(readersService.findOne(reader.getFullName()) !=  null){
            errors.rejectValue("fullName","","This Full Name is already registered");
        }

    }
}