package com.sonle.testmodule4.validate;

import com.sonle.testmodule4.model.City;
import com.sonle.testmodule4.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ValidateName implements Validator {
    @Autowired
    ICityService cityService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        City city = (City) target;
        List<City> cityList = cityService.findAll();
        for (City c: cityList) {
            if (city.getCityName().equals(c.getCityName()) && city.getId() != c.getId()){
                errors.rejectValue("cityName","","Ten da ton tai, moi nhap ten khac");
                return;
            }
        }
    }
}