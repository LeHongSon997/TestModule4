package com.sonle.testmodule4.service;

import com.sonle.testmodule4.model.City;
import com.sonle.testmodule4.repository.ICityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService implements ICityService {
    @Autowired
    ICityRepo cityRepo;

    @Override
    public List<City> findAll() {
        return (List<City>) cityRepo.findAll();
    }

    @Override
    public Optional<City> findById(long id) {
        return cityRepo.findById(id);
    }

    @Override
    public void save(City city) {
        cityRepo.save(city);
    }

    @Override
    public void delete(long id) {
        cityRepo.deleteById(id);
    }

    @Override
    public List<City> findAll(Sort sort) {
        return (List<City>) cityRepo.findAll(sort);
    }

    @Override
    public List<City> findByName(String name) {
        return cityRepo.findAllByName(name);
    }

    @Override
    public Page<City> findAll(Pageable pageable) {
        return cityRepo.findAll(pageable);
    }
}
