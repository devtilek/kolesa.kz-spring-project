package practice.kolesokz.services;

import practice.kolesokz.Entities.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long id);
    Country create(Country country);
    void delete(Long id);
}
