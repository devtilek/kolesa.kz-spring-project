package practice.kolesokz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import practice.kolesokz.Entities.Car;
import practice.kolesokz.Entities.CarStatus;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    List<Car> findAll();
    Page<Car> search(String name, BigDecimal minPrice, BigDecimal maxPrice, Long countryId, CarStatus status, Pageable pageable);
    Car findById(Long id);
    Car create(Car car, Long countryId);
    Car update(Long id, Car car, Long countryId);
    void delete(Long id);
}
