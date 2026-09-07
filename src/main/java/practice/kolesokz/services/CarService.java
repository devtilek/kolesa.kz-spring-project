package practice.kolesokz.services;

import practice.kolesokz.Entities.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car findById(Long id);
    Car create(Car car, Long countryId);
    Car update(Long id, Car car, Long countryId);
    void delete(Long id);
}
