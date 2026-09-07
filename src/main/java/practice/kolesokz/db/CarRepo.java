package practice.kolesokz.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.kolesokz.Entities.Car;
import practice.kolesokz.Entities.CarStatus;

import java.math.BigDecimal;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    Page<Car> findByNameContainingIgnoreCaseAndStatus(String name, CarStatus status, Pageable pageable);

    Page<Car> findByPriceBetweenAndStatus(BigDecimal minPrice, BigDecimal maxPrice, CarStatus status, Pageable pageable);

    Page<Car> findByCountryIdAndStatus(Long countryId, CarStatus status, Pageable pageable);
}
