package ru.ialexdm.springapp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ialexdm.springapp.dao.ReaderDAO;
import ru.ialexdm.springapp.models.Reader;

@Component
public class ReaderValidator implements Validator {
    private final ReaderDAO readerDAO;

    public ReaderValidator(ReaderDAO readerDAO) {
        this.readerDAO = readerDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Reader.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Reader reader = (Reader) target;
        if(readerDAO.show(reader.getFullName()) !=  null){
            errors.rejectValue("fullName","","This Full Name is already registered");
        }

    }
}