package mvc.backend.backendserver.repository;


import mvc.backend.backendserver.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {
    Account findAccountByUsername(String username);
    boolean existsAccountByUsername(String username);
    boolean existsAccountByEmail(String email);
    boolean existsAccountByPhoneNumber(String phone);
    Account findAccountById(int id);
}
