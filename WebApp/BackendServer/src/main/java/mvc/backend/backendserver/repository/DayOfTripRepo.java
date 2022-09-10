package mvc.backend.backendserver.repository;

import mvc.backend.backendserver.entity.DayOfTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayOfTripRepo extends JpaRepository<DayOfTrip, Integer> {
}
