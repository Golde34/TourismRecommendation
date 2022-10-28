package mvc.backend.backendserver.repository;

import mvc.backend.backendserver.entity.RestingPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestingPlaceRepo extends JpaRepository<RestingPlace, Integer> {
}
