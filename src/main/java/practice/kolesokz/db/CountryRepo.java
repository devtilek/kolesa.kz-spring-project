package practice.kolesokz.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import practice.kolesokz.Entities.Country;

@Repository
@Transactional
public interface CountryRepo extends JpaRepository<Country, Long> {
}
