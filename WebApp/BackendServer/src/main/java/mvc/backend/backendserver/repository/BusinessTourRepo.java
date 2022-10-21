package mvc.backend.backendserver.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import mvc.backend.backendserver.entity.Account;
import mvc.backend.backendserver.entity.Distance;
import mvc.backend.backendserver.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface BusinessTourRepo extends JpaRepository<Tour, Integer> {

    @Query(value = "SELECT * FROM tourismrecommendation.tour t WHERE t.account_id = ?1",
            nativeQuery = true)
    ArrayList<Tour> getToursByAccount(int accountId);
    Tour getTourById(int id);

}
