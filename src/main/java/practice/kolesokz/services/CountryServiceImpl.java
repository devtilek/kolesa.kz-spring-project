package practice.kolesokz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.kolesokz.Entities.Country;
import practice.kolesokz.Exceptions.ResourceNotFoundException;
import practice.kolesokz.db.CountryRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService {

    private final CountryRepo countryRepo;

    @Override
    public List<Country> findAll() {
        return countryRepo.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found: " + id));
    }

    @Override
    @Transactional
    public Country create(Country country) {
        country.setId(null);
        return countryRepo.save(country);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!countryRepo.existsById(id)) {
            throw new ResourceNotFoundException("Country not found: " + id);
        }
        countryRepo.deleteById(id);
    }
}
