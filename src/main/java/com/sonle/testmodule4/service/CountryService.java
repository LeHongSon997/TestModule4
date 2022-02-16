package com.sonle.testmodule4.service;

import com.sonle.testmodule4.model.Country;
import com.sonle.testmodule4.repository.ICountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements ICountryService {
    @Autowired
    ICountryRepo countryRepo;
    @Override
    public List<Country> findAll() {
        return (List<Country>) countryRepo.findAll();
    }

    @Override
    public Optional<Country> findById(long id) {
        return countryRepo.findById(id);
    }
}
