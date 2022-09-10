package mvc.backend.backendserver.repository;

import mvc.backend.backendserver.entity.POIOfDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface POIOfDayRepo extends JpaRepository<POIOfDay, Integer> {

    public POIOfDay findPOIOfDayById(int id);
}
