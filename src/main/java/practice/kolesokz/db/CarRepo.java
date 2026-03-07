package practice.kolesokz.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import practice.kolesokz.Entities.Car;
@Repository
@Transactional
public interface CarRepo extends JpaRepository<Car, Long> {
}
