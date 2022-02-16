package com.sonle.testmodule4.repository;

import com.sonle.testmodule4.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICityRepo extends PagingAndSortingRepository<City, Long> {
    @Query(value = "select c from City c where c.cityName like concat('%' ,:name, '%')")
    List<City> findAllByName(@Param("name") String name);
}
