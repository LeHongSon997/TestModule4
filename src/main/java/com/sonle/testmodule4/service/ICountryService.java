package com.sonle.testmodule4.service;

import com.sonle.testmodule4.model.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService {
    public List<Country> findAll();
    public Optional<Country> findById(long id);
}
