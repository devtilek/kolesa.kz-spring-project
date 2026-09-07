package practice.kolesokz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.kolesokz.Entities.Car;
import practice.kolesokz.Entities.CarStatus;
import practice.kolesokz.Entities.Country;
import practice.kolesokz.Exceptions.ResourceNotFoundException;
import practice.kolesokz.db.CarRepo;
import practice.kolesokz.db.CountryRepo;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

    private final CarRepo carRepo;
    private final CountryRepo countryRepo;

    @Override
    public List<Car> findAll() {
        return carRepo.findAll();
    }

    @Override
    public Page<Car> search(String name, BigDecimal minPrice, BigDecimal maxPrice,
                            Long countryId, CarStatus status, Pageable pageable) {
        Specification<Car> specification = Specification.where(null);

        if (name != null && !name.isBlank()) {
            specification = specification.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (minPrice != null) {
            specification = specification.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        if (countryId != null) {
            specification = specification.and((root, query, cb) ->
                    cb.equal(root.get("country").get("id"), countryId));
        }
        if (status != null) {
            specification = specification.and((root, query, cb) ->
                    cb.equal(root.get("status"), status));
        }

        return carRepo.findAll(specification, pageable);
    }

    @Override
    public Car findById(Long id) {
        return carRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found: " + id));
    }

    @Override
    @Transactional
    public Car create(Car car, Long countryId) {
        car.setId(null);
        car.setCountry(findCountry(countryId));
        return carRepo.save(car);
    }

    @Override
    @Transactional
    public Car update(Long id, Car car, Long countryId) {
        Car existing = findById(id);
        existing.setName(car.getName());
        existing.setPrice(car.getPrice());
        existing.setYear(car.getYear());
        existing.setMileage(car.getMileage());
        existing.setFuelType(car.getFuelType());
        existing.setTransmission(car.getTransmission());
        existing.setCity(car.getCity());
        existing.setStatus(car.getStatus());
        existing.setUrl(car.getUrl());
        existing.setDescription(car.getDescription());
        existing.setCountry(findCountry(countryId));
        return carRepo.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!carRepo.existsById(id)) {
            throw new ResourceNotFoundException("Car not found: " + id);
        }
        carRepo.deleteById(id);
    }

    private Country findCountry(Long id) {
        return countryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found: " + id));
    }
}
