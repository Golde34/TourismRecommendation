package mvc.backend.backendserver.repository;

import mvc.backend.backendserver.entity.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceRepo extends JpaRepository<Distance,Integer> {
}
