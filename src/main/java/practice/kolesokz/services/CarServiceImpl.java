package practice.kolesokz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.kolesokz.Entities.Car;
import practice.kolesokz.Entities.Country;
import practice.kolesokz.Exceptions.ResourceNotFoundException;
import practice.kolesokz.db.CarRepo;
import practice.kolesokz.db.CountryRepo;

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
