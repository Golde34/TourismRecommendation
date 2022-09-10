package mvc.backend.backendserver.repository;

import mvc.backend.backendserver.entity.MyPOI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface POIRepo extends JpaRepository<MyPOI, Integer> {
    public MyPOI findByPOIId(int POIId);
}
