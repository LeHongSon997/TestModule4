package com.sonle.testmodule4.service;

import com.sonle.testmodule4.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ICityService {
    public List<City> findAll();
    public Optional<City> findById(long id);
    public void save(City city);
    public void delete(long id);
    public List<City> findAll(Sort sort);
    public List<City> findByName (String name);
    public Page<City> findAll(Pageable pageable);
}
