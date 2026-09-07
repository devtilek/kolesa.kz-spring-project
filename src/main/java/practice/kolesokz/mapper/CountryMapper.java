package practice.kolesokz.mapper;

import org.springframework.stereotype.Component;
import practice.kolesokz.Entities.Country;
import practice.kolesokz.dto.CountryRequest;
import practice.kolesokz.dto.CountryResponse;

@Component
public class CountryMapper {

    public Country toEntity(CountryRequest request) {
        Country country = new Country();
        country.setName(request.name());
        country.setUrl(request.url());
        return country;
    }

    public CountryResponse toResponse(Country country) {
        return new CountryResponse(country.getId(), country.getName(), country.getUrl());
    }
}
