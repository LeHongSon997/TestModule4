package com.sonle.testmodule4.repository;

import com.sonle.testmodule4.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface ICountryRepo extends CrudRepository<Country, Long> {
}
