package practice.kolesokz.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import practice.kolesokz.Entities.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
}
